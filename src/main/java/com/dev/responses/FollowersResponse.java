package com.dev.responses;

import com.dev.Models.FriendsDetailsModel;
import com.dev.objects.User;

import java.util.ArrayList;
import java.util.List;

public class FollowersResponse extends BasicResponse{
    private List<FriendsDetailsModel> followers;


    public FollowersResponse(boolean success, Integer errorCode,List<User> followers) {
        super(success, errorCode);
        this.followers = convertUsersListToFriendModel(followers);
    }

    public List<FriendsDetailsModel> getFollowers() {
        return followers;
    }

    public void setFollowers(List<FriendsDetailsModel> followers) {
        this.followers = followers;
    }

    private List<FriendsDetailsModel> convertUsersListToFriendModel(List<User> users){
        List<FriendsDetailsModel> friendsDetailsModels=new ArrayList<>();
        for (User user :users) {
            FriendsDetailsModel friendsDetailsModel=new FriendsDetailsModel(user);
            friendsDetailsModels.add(friendsDetailsModel);
        }
        return friendsDetailsModels;
    }

}
