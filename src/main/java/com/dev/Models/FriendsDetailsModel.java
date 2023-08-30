package com.dev.Models;

import com.dev.objects.User;

public class FriendsDetailsModel {
    private int id;
   private String username;
    private String picture;

    public FriendsDetailsModel(User user){
        this.id=user.getId();
        this.username=user.getUsername();
        this.picture=user.getPicture();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
