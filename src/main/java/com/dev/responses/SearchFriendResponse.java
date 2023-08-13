package com.dev.responses;

import com.dev.Models.FriendsDetailsModel;
import com.dev.objects.User;

public class SearchFriendResponse extends BasicResponse {
     private FriendsDetailsModel friendsDetailsModel;

    public SearchFriendResponse(FriendsDetailsModel friendsDetailsModel) {
        this.friendsDetailsModel = friendsDetailsModel;
    }

    public SearchFriendResponse(boolean success, Integer errorCode, FriendsDetailsModel friendsDetailsModel) {
        super(success, errorCode);
       this.friendsDetailsModel=friendsDetailsModel;
    }

    public FriendsDetailsModel getFriendsDetailsModel() {
        return friendsDetailsModel;
    }

    public void setFriendsDetailsModel(FriendsDetailsModel friendsDetailsModel) {
        this.friendsDetailsModel = friendsDetailsModel;
    }
}
