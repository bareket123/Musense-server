package com.dev.controllers;

import com.dev.Models.FriendsDetailsModel;
import com.dev.objects.User;
import com.dev.objects.UserConnection;
import com.dev.responses.BasicResponse;
import com.dev.responses.GetFriendsResponse;
import com.dev.responses.SearchFriendResponse;
import com.dev.utils.Errors;
import com.dev.utils.Persist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
           basicResponse=new BasicResponse(false, Errors.ERROR_NOT_FOUND_USER);
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
            basicResponse=new BasicResponse(false,Errors.ERROR_NOT_FOUND_USER);
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
            basicResponse=new BasicResponse(false,Errors.ERROR_NOT_FOUND_USER);

        }
        return basicResponse;
        }






    }





