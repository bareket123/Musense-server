package com.dev.Models;

import com.dev.objects.User;

public class UserDetailsModel {
    private String username;
    private String email;
    private String picture;


    public UserDetailsModel(User user){
        this.username=user.getUsername();
        this.email=user.getEmail();
        this.picture=user.getPicture();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
