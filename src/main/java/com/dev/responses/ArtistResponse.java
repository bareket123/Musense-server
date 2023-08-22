package com.dev.responses;

import java.util.List;

public class ArtistResponse extends BasicResponse{
    private List<String> lovedArtists;

    public ArtistResponse(List<String> lovedArtists) {
        this.lovedArtists = lovedArtists;
    }

    public ArtistResponse(boolean success, Integer errorCode, List<String> lovedArtists) {
        super(success, errorCode);
        this.lovedArtists = lovedArtists;
    }

    public List<String> getLovedArtists() {
        return lovedArtists;
    }

    public void setLovedArtists(List<String> lovedArtists) {
        this.lovedArtists = lovedArtists;
    }
}
