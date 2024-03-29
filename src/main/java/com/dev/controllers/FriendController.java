package com.dev.controllers;

import com.dev.Models.FriendsDetailsModel;
import com.dev.Models.SearchFriendModel;
import com.dev.objects.User;
import com.dev.objects.UserConnection;
import com.dev.responses.*;
import com.dev.utils.Errors;
import com.dev.utils.Persist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FriendController {

    @Autowired
    Persist persist;




    @RequestMapping(value = "search-by-user-username")
    public BasicResponse getUserByUsername(String username){
        BasicResponse basicResponse;
        User foundUser=persist.getUserByUsername(username);
      if (foundUser!=null){
          FriendsDetailsModel friendsDetailsModel=new FriendsDetailsModel(foundUser);
           basicResponse=new SearchFriendResponse(true,null,friendsDetailsModel);
      }else {
           basicResponse=new BasicResponse(false, Errors.ERROR_USER_NOT_FOUND);
      }
      return basicResponse;
    }
    @RequestMapping(value = "follow-friend")
    public BasicResponse createAFriendFollowingRequest(String token,String friendUsername){
        BasicResponse basicResponse;
        User user=persist.getUserByToken(token);
        User friend=persist.getUserByUsername(friendUsername);
        if (user!=null){
            if (friend!=null){
                persist.createAFriendRequest(user,friend);
                basicResponse=new BasicResponse(true,null);
            }else {
                basicResponse=new BasicResponse(false,Errors.ERROR_NOT_FOUND_FRIEND);
            }

        }else {
            basicResponse=new BasicResponse(false,Errors.ERROR_USER_NOT_FOUND);
        }
     return basicResponse;
    }
    @RequestMapping(value = "get-my-friends")
    public BasicResponse getMyFriends(String token){
        BasicResponse basicResponse;
        List<User> allMyFriends=null;
        User user=persist.getUserByToken(token);
        if (user!=null){
         allMyFriends =persist.getMyFriends(user);

         basicResponse=new GetFriendsResponse(true,null,allMyFriends);
         }else {
            basicResponse=new BasicResponse(false,Errors.ERROR_USER_NOT_FOUND);

        }
        return basicResponse;
        }


   @RequestMapping(value = "delete-friend")
    public BasicResponse deleteFriend(String token ,String friendUsername,int deleteStatus){
     BasicResponse basicResponse;
     UserConnection userConnection;
     User friendToDelete=persist.getUserByUsername(friendUsername);
     User user=persist.getUserByToken(token);
     if (user!=null&&friendToDelete!=null){
         if (deleteStatus==1){
              userConnection=persist.getConnection(user,friendToDelete);
         }else {
              userConnection=persist.getConnection(friendToDelete,user);
         }
         if (userConnection!=null){
             persist.deleteConnection(userConnection);
             basicResponse=new BasicResponse(true,null);
         }else {
             basicResponse=new BasicResponse(false,Errors.ERROR_NO_SUCH_CONNECTION);
         }
     }else {
         basicResponse=new BasicResponse(false,Errors.ERROR_USER_NOT_FOUND);
     }

     return basicResponse;
    }

    @RequestMapping(value = "get-all-Users-without-current")
    public BasicResponse getAllUsersWithoutCurrentUser(String token){
        List <User> users ;
        BasicResponse basicResponse;
        User currentUser=persist.getUserByToken(token);
        if (currentUser!=null){
            users=persist.getAllUsersWithoutCurrentUser(token);
            List <SearchFriendModel> allUsers=new ArrayList<>();
            for (User current: users) {
                SearchFriendModel user = new SearchFriendModel(current, persist.isFollowing(current,persist.getMyFriends(currentUser)));
                allUsers.add(user);
         }

            basicResponse= new SearchResponse(true,null,allUsers);
        }else {
            basicResponse=new BasicResponse(false, Errors.ERROR_USER_NOT_FOUND);
        }
        return basicResponse;
    }
    @RequestMapping (value = "get-followers")
    public BasicResponse getUserFollowers(String token){
        BasicResponse basicResponse;
        User user=persist.getUserByToken(token);
        if (user!=null){
            List<User> userFollowers=persist.getUserFollowings(user);
            if (userFollowers!=null){
                basicResponse=new FollowersResponse(true,null,userFollowers);
            }else {
                basicResponse=new BasicResponse(false,Errors.ERROR_NOT_FOUND_FOLLOWERS);
            }
        }else {
            basicResponse=new BasicResponse(false,Errors.ERROR_USER_NOT_FOUND);
        }



        return basicResponse;
    }



    }






