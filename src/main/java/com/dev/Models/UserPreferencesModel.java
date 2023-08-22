package com.dev.Models;

import com.dev.objects.UserPreferences;

public class UserPreferencesModel {
    private String genre;
    private String artist1;
    private String artist2;
    private String favoriteSong;

    public UserPreferencesModel(UserPreferences userPreferences){
        this.genre=userPreferences.getGenre();
        this.artist1=userPreferences.getArtist1();
        this.artist2=userPreferences.getArtist2();
        this.favoriteSong=userPreferences.getFavoriteSong();
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
