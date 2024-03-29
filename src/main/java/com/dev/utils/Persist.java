
package com.dev.utils;

import com.dev.Models.UserDetailsModel;
import com.dev.objects.Song;
import com.dev.objects.User;
import com.dev.objects.UserConnection;
import com.dev.objects.UserPreferences;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class Persist {

    private final SessionFactory sessionFactory;

    @Autowired
    public Persist(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public User getUserByUsername(String username) {
        User found;
        Session session = sessionFactory.openSession();
        found = (User) session.createQuery("FROM User WHERE username = :username")
                .setParameter("username", username)
                .uniqueResult();
        session.close();
        return found;
    }

    public void saveUser(User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
    }


    public User getUserByUsernameAndToken(String username, String token) {
        User found;
        Session session = sessionFactory.openSession();
        found = (User) session.createQuery("FROM User WHERE username = :username " +
                        "AND token = :token")
                .setParameter("username", username)
                .setParameter("token", token)
                .uniqueResult();
        session.close();
        return found;
    }

    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> allUsers = session.createQuery("FROM User ").list();
        session.close();
        return allUsers;
    }

    public List<User> getAllUsersWithoutCurrentUser(String token) {
        Session session = sessionFactory.openSession();
        List<User> allUsers = session.createQuery("FROM User ").list();
        session.close();
        List<User> users= allUsers.stream().filter((user -> !Objects.equals(user.getToken(), token))).collect(Collectors.toList());
        return users;
    }
    public User getUserByToken(String token) {
        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery("From User WHERE token = :token")
                .setParameter("token", token)
                .uniqueResult();
        session.close();
        return user;
    }

    public UserDetailsModel getUsernameByToke(String token) {
        UserDetailsModel userDetailsModel = null;
        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery("From User WHERE token = :token")
                .setParameter("token", token)
                .uniqueResult();
        session.close();
        if (user != null) {
            userDetailsModel = new UserDetailsModel(user);
        }
        return userDetailsModel;
    }

    public void createAFriendRequest(User user, User friend) {
        Session session = sessionFactory.openSession();
        UserConnection newConnection = new UserConnection(user, friend);
        session.save(newConnection);
        session.close();
    }

    public List<UserConnection> followingRequestByUser(User user) {
        Session session = sessionFactory.openSession();

        List<UserConnection> followingRequestByUsers = session
                .createQuery("FROM UserConnection WHERE user = :user")
                .setParameter("user", user)
                .list();
        session.close();
        return followingRequestByUsers;
    }

    public List<User> getMyFriends(User user) {
        List<User> allMyFriends = new ArrayList<>();
        List<UserConnection> allFollowingFriendsRequest = followingRequestByUser(user);
        for (UserConnection userConnection : allFollowingFriendsRequest) {
            allMyFriends.add(userConnection.getFriend());

        }

        return allMyFriends;
    }

    public boolean isFollowing ( User other,List<User> allMyFriends){
        boolean isFollowing =false;
        if (allMyFriends.size()!=0)
        for (User user : allMyFriends ) {
            if (user.getId()==other.getId()){
                isFollowing=true;
                break;
            }
        }
        return isFollowing;
    }



    public void addToSong(Song song) {
        Session session = sessionFactory.openSession();
        session.save(song);
        session.close();
    }

    public List<Song> getUserPlaylist(User user,boolean isPlayed){
        Session session= sessionFactory.openSession();
        List<Song>  playlistByUser= session.createQuery("from Song where user= :user AND isPlayed= :isPlayed").setParameter("user",user).setParameter("isPlayed",isPlayed).list();
        session.close();
        return playlistByUser;
    }
    public void deleteSong(Song song){
        Session session=sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(song);
        transaction.commit();
        session.close();
    }
    public void deleteConnection(UserConnection userConnection){
        Session session=sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(userConnection);
        transaction.commit();
        session.close();
    }
    public Song getSongById(int id){
        Session session=sessionFactory.openSession();
        Song song= (Song) session.createQuery("from Song where id= :id").setParameter("id",id).uniqueResult();
        session.close();
        return song;

    }
//public List<String> artistUserHas(User user){
//        List<String> artists=new ArrayList<>();
//        List<Song> playlist=getUserPlaylist(user);
//        if (playlist!=null){
//            for (Song song:playlist) {
//                if (!artists.contains(song.getArtist())){
//                    artists.add(song.getArtist());
//                }
//            }
//        }
//
// return artists;
//
//}
public void addUserPreferences(UserPreferences userPreferences){
        Session session= sessionFactory.openSession();
        session.save(userPreferences);
        session.close();
}
public UserPreferences getUserPreferences(User user){
        Session session= sessionFactory.openSession();
        UserPreferences userPreferences=(UserPreferences) session.createQuery("from UserPreferences where user= :user").setParameter("user",user).uniqueResult();
        session.close();
        return userPreferences;
}
public UserConnection getConnection(User user,User friend){
    Session session= sessionFactory.openSession();
    UserConnection userConnection = (UserConnection) session.createQuery("FROM UserConnection where user= :user And friend= :friend").setParameter("user",user).
            setParameter("friend",friend).uniqueResult();
    session.close();
    return userConnection;
}
public void deleteUserPreferences (UserPreferences userPreferences){
    Session session=sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    session.delete(userPreferences);
    transaction.commit();
    session.close();

}
public List<Song> getUserPlayedRecently(User user,boolean isPlayed){
    Session session= sessionFactory.openSession();
    List<Song>  playedRecently= session.createQuery("from Song where user= :user AND isPlayed= :isPlayed").setParameter("user",user).setParameter("isPlayed",isPlayed).list();
    session.close();
    return playedRecently;

}
    public void deleteUserPlayedRecently (List<Song> playedRecently){
        Session session=sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            for (Song song : playedRecently) {
                session.delete(song);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

    }
    public List<User> getUserFollowings(User user){
        List <User> followers=new ArrayList<>();
        Session session=sessionFactory.openSession();
       List<UserConnection>allUserConnections=session.createQuery("FROM UserConnection where friend= :user").setParameter("user",user).list();
       if (allUserConnections!=null){
           for (UserConnection userConnection:allUserConnections) {
               followers.add(userConnection.getUser());
           }
       }
       session.close();
     return followers;
    }



}
