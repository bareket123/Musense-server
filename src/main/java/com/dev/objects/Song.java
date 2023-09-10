package com.dev.objects;

import javax.persistence.*;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String title;
    @Column
    private String artist;
    @Column
    private String url;
    @Column
    private String coverImage;
    @Column
    private boolean isPlayed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Song(String title, String artist, String url, String coverImage,User user,boolean isPlayed) {

        this.title = title;
        this.artist = artist;
        this.url = url;
        this.coverImage = coverImage;
        this.user=user;
        this.isPlayed=isPlayed;

    }

    public Song() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String singer) {
        this.artist = singer;
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(boolean played) {
        isPlayed = played;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
