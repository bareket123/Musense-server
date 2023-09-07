package com.dev.responses;

import com.dev.Models.SearchFriendModel;

import java.util.List;

public class SearchResponse extends BasicResponse{
    private List<SearchFriendModel> myFriends;

    public SearchResponse(List<SearchFriendModel> myFriends) {
        this.myFriends = myFriends;
    }

    public SearchResponse(boolean success, Integer errorCode, List<SearchFriendModel> myFriends) {
        super(success, errorCode);
        this.myFriends = myFriends;
    }

    public List<SearchFriendModel> getMyFriends() {
        return myFriends;
    }

    public void setMyFriends(List<SearchFriendModel> myFriends) {
        this.myFriends = myFriends;
    }
}
