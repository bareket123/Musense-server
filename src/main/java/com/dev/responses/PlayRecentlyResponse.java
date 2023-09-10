package com.dev.responses;

import com.dev.objects.Song;

import java.util.List;

public class PlayRecentlyResponse extends BasicResponse{
    private List<Song> playedRecently;


    public PlayRecentlyResponse(List<Song> playedRecently) {
        this.playedRecently = playedRecently;
    }

    public PlayRecentlyResponse(boolean success, Integer errorCode, List<Song> playedRecently) {
        super(success, errorCode);
        this.playedRecently = playedRecently;
    }

    public List<Song> getPlayedRecently() {
        return playedRecently;
    }

    public void setPlayedRecently(List<Song> playedRecently) {
        this.playedRecently = playedRecently;
    }
}
