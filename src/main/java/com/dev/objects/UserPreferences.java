package com.dev.objects;

import javax.persistence.*;

@Entity
@Table(name = "users_preferences")
public class UserPreferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "user_id")
    @OneToOne
    private User user;

    @Column
    private String genre;

    @Column
    private String artist1;
    @Column
    private String artist2;

    @Column
    private String favoriteSong;

    public UserPreferences() {

    }
    public UserPreferences(User user, String genre, String artist1, String artist2, String favoriteSong) {
        this.user = user;
        this.genre = genre;
        this.artist1 = artist1;
        this.artist2 = artist2;
        this.favoriteSong = favoriteSong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtist1() {
        return artist1;
    }

    public void setArtist1(String artist1) {
        this.artist1 = artist1;
    }

    public String getArtist2() {
        return artist2;
    }

    public void setArtist2(String artist2) {
        this.artist2 = artist2;
    }

    public String getFavoriteSong() {
        return favoriteSong;
    }

    public void setFavoriteSong(String favoriteSong) {
        this.favoriteSong = favoriteSong;
    }


}
