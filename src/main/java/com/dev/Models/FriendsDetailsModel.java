package com.dev.Models;

import com.dev.objects.User;

public class FriendsDetailsModel {
   private String username;
    private String picture;

    public FriendsDetailsModel(User user){
        username=user.getUsername();
        picture=user.getPicture();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
