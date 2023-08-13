package com.dev.objects;

import com.dev.utils.Constants;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String picture;
    @Column
    private String username;

    @Column
    private String token;
    @Column
    private String email;


    public User(String username, String token,String email,String pictureLink) {
        this.username = username;
        this.token = token;
        this.email=email;
        this.picture=pictureLink;
    }

    public User() {

    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
