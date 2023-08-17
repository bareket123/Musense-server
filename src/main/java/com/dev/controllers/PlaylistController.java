package com.dev.controllers;

import com.dev.objects.Song;
import com.dev.objects.User;
import com.dev.responses.BasicResponse;
import com.dev.responses.GetFriendsResponse;
import com.dev.responses.GetPlaylistResponse;
import com.dev.utils.Errors;
import com.dev.utils.Persist;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
public class PlaylistController {
  @Autowired
    Persist persist;


  @RequestMapping(value = {"add-song"})
    public BasicResponse addSong(String token,String title,String artist,String url,String coverImage){
      BasicResponse basicResponse;
        User user=persist.getUserByToken(token);
        if (user!=null){
            System.out.println("title=" +title);
            System.out.println("artist=" +artist);
            System.out.println("url=" +url);
            System.out.println("cover=" +coverImage);
          if (title!=null & artist!=null&url!=null&coverImage!=null){
              Song songToAdd=new Song(title,artist,url,coverImage,user);
              persist.addToSong(songToAdd);

              basicResponse=new BasicResponse(true,null);
          }else{
              basicResponse=new BasicResponse(false,Errors.ERROR_WRONG_SONG_DETAILS);
          }

        }else {
            basicResponse=new BasicResponse(false, Errors.ERROR_NOT_FOUND_USER);
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
          basicResponse=new BasicResponse(false,Errors.ERROR_NOT_FOUND_USER);
      }
      return basicResponse;
    }

    //לשנות לפי id
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

}
