package com.dev.responses;

import com.dev.Models.FriendsDetailsModel;
import com.dev.objects.User;

import java.util.ArrayList;
import java.util.List;

public class GetFriendsResponse extends BasicResponse {
    private List<FriendsDetailsModel> myFriends;

    public GetFriendsResponse(List<FriendsDetailsModel> myFriends) {
        this.myFriends = myFriends;
    }

    public GetFriendsResponse(boolean success, Integer errorCode, List<User> myFriends) {
        super(success, errorCode);
      this.myFriends= convertUsersToFriendModels(myFriends);
    }

    public List<FriendsDetailsModel> getMyFriends() {
        return myFriends;
    }

    public void setMyFriends(List<FriendsDetailsModel> myFriends) {
        this.myFriends = myFriends;
    }

    private List<FriendsDetailsModel> convertUsersToFriendModels(List<User> friends){
        List<FriendsDetailsModel> friendsDetailsModels=new ArrayList<>();
        for (User user:friends) {
            FriendsDetailsModel friendsDetailsModel=new FriendsDetailsModel(user);
            friendsDetailsModels.add(friendsDetailsModel);
        }

       return friendsDetailsModels;
    }
}


