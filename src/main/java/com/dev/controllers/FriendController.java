package com.dev.controllers;

import com.dev.Models.FriendsDetailsModel;
import com.dev.objects.User;
import com.dev.objects.UserConnection;
import com.dev.objects.UserConnectionService;
import com.dev.responses.BasicResponse;
import com.dev.responses.SearchFriendResponse;
import com.dev.utils.Errors;
import com.dev.utils.Persist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Basic;
@RestController
public class FriendController {
//    @Autowired
//    private UserConnectionService connectionService;
    @Autowired
    Persist persist;

    //search friends
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
//
//    @PostMapping("/sendRequest")
//    public ResponseEntity<UserConnection> sendFriendRequest() {
//        UserConnection connection = connectionService.sendFriendRequest(requestDTO.getUserId(), requestDTO.getFriendId());
//        return ResponseEntity.status(HttpStatus.CREATED).body(connection);
//    }
//
//    @PostMapping("/acceptRequest")
//    public ResponseEntity<Void> acceptFriendRequest(@RequestParam Long connectionId) {
//        connectionService.acceptFriendRequest(connectionId);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/declineRequest")
//    public ResponseEntity<Void> declineFriendRequest(@RequestParam Long connectionId) {
//        connectionService.declineFriendRequest(connectionId);
//        return ResponseEntity.ok().build();
//    }




}
