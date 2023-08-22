package com.dev.controllers;

import com.dev.objects.Song;
import com.dev.objects.User;
import com.dev.responses.*;
import com.dev.utils.Errors;
import com.dev.utils.Persist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;



@RestController
public class PlaylistController {
  @Autowired
    Persist persist;
  @Autowired
  LiveUpdateController liveUpdateController;


  @RequestMapping(value = {"add-song"})
    public BasicResponse addSong(String token,String title,String artist,String url,String coverImage){
      BasicResponse basicResponse;
        User user=persist.getUserByToken(token);
        if (user!=null){
          if (title!=null & artist!=null&url!=null&coverImage!=null){
              Song songToAdd=new Song(title,artist,url,coverImage,user);
              persist.addToSong(songToAdd);

              basicResponse=new BasicResponse(true,null);
          }else{
              basicResponse=new BasicResponse(false,Errors.ERROR_WRONG_SONG_DETAILS);
          }

        }else {
            basicResponse=new BasicResponse(false, Errors.ERROR_USER_NOT_FOUND);
        }

      return basicResponse;
  }

    @RequestMapping(value = {"get-playlist"})
    public BasicResponse getPlaylist(String token){
     BasicResponse basicResponse;
      User user=persist.getUserByToken(token);
      if (user!=null){
       List<Song> playlist=persist.getUserPlaylist(user);
       if (playlist!=null){
           basicResponse=new GetPlaylistResponse(true,null,playlist);
       }else {
           basicResponse=new BasicResponse(false,Errors.ERROR_PLAYLIST_NOT_EXIST);
       }
      }else{
          basicResponse=new BasicResponse(false,Errors.ERROR_USER_NOT_FOUND);
      }
      return basicResponse;
    }


    @RequestMapping(value = {"delete-song"})
    public BasicResponse deleteSongFromPlaylist(int songId){
      BasicResponse basicResponse;
      Song song=persist.getSongById(songId);
      if (song!=null){
          persist.deleteSong(song);
          basicResponse=new BasicResponse(true,null);

      }else {
          basicResponse=new BasicResponse(false,Errors.ERROR_WRONG_SONG_DETAILS);
      }

      return basicResponse;
    }
    @RequestMapping(value = "get-friends-playlist")
    public BasicResponse getPlaylistByFriends(String token){
      List<Song> playlistByFriends=new ArrayList<>();
      BasicResponse basicResponse;
      User user=persist.getUserByToken(token);
      if (user!=null){
          List<User> myFriends=persist.getMyFriends(user);
          for (User friend: myFriends) {
              List<Song>playlistByCurrentFriend=persist.getUserPlaylist(friend);
                  assert playlistByCurrentFriend!=null;
                  playlistByFriends.addAll(playlistByCurrentFriend);
          }
          basicResponse=new PlaylistByFriendsResponse(true,null,playlistByFriends);
      }else {
          basicResponse=new BasicResponse(false,Errors.ERROR_USER_NOT_FOUND);
      }

           return basicResponse;
    }

    @RequestMapping(value = "get-love-artist")
    public BasicResponse getArtists(String userToken){
      BasicResponse basicResponse;
      User user=persist.getUserByToken(userToken);
      if (user!=null){
          List<String> artists=persist.artistUserHas(user);
          if (artists.size()>0){
              basicResponse=new ArtistResponse(true,null,artists);
          }else {
              basicResponse=new BasicResponse(false,Errors.ERROR_PLAYLIST_NOT_EXIST);
          }
      }else {
          basicResponse=new BasicResponse(false,Errors.ERROR_USER_NOT_FOUND);
      }
      return basicResponse;
    }
    @RequestMapping(value = "is-song-exist")
    public boolean isSongExist(int songId,String token) {
        Song song = persist.getSongById(songId);
        boolean exist = false;
        if (song != null) {
            System.out.println("the song is: " + song.getTitle() + " " + song.getArtist());
            User user = persist.getUserByToken(token);
            if (user != null) {
                System.out.println("user is: " + user.getUsername());
                List<Song> playlist = persist.getUserPlaylist(user);
                for (Song current : playlist) {
                    if (current.getId() == song.getId()) {
                        exist = true;
                        break;
                    }
                }

            }

        }
        return exist;
    }
}
