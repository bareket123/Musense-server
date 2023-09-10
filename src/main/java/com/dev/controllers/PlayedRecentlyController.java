package com.dev.controllers;

import com.dev.objects.Song;
import com.dev.objects.User;
import com.dev.responses.BasicResponse;
import com.dev.responses.PlayRecentlyResponse;
import com.dev.utils.Errors;
import com.dev.utils.Persist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayedRecentlyController {
    @Autowired
    Persist persist;

@RequestMapping(value = "get-played-recently")
public BasicResponse getPlayedRecently(String token){
    BasicResponse basicResponse;
    User user=persist.getUserByToken(token);
    if (user!=null){
        List<Song> playedRecently=persist.getUserPlayedRecently(user,true);
        if (playedRecently!=null){
            basicResponse=new PlayRecentlyResponse(true,null,playedRecently);
        }else {
            basicResponse=new BasicResponse(false,Errors.ERROR_NOT_FOUND_PLAYED_RECENTLY);
        }
    }else {
        basicResponse=new BasicResponse(false,Errors.ERROR_USER_NOT_FOUND);
    }
    return basicResponse;
}
@RequestMapping(value = "delete-played-recently")
    public BasicResponse deletePlayedRecently(String token){
    BasicResponse basicResponse;
    User user=persist.getUserByToken(token);
    if (user!=null){
        List<Song> playedRecently=persist.getUserPlayedRecently(user,true);
        if (playedRecently!=null){
            persist.deleteUserPlayedRecently(playedRecently);
            basicResponse=new BasicResponse(true,null);
        }else {
            basicResponse=new BasicResponse(false,Errors.ERROR_NOT_FOUND_PLAYED_RECENTLY);
        }
    }else {
        basicResponse=new BasicResponse(false,Errors.ERROR_USER_NOT_FOUND);
    }
    return basicResponse;
   }



}
