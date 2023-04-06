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
    private String name;
    @Column
    private String dateOfPublication;

    @Column
    private String genre;

    @Column
    private int popularityDimension;

    @Column
    private String singer;

    public Song(String name, String dateOfPublication, String genre, int popularityDimension, String singer) {
        this.name = name;
        this.dateOfPublication = dateOfPublication;
        this.genre = genre;
        this.popularityDimension = popularityDimension;
        this.singer = singer;
    }

    public Song() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(String dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPopularityDimension() {
        return popularityDimension;
    }

    public void setPopularityDimension(int popularityDimension) {
        this.popularityDimension = popularityDimension;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
