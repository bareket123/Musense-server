package com.dev.responses;

import com.dev.objects.Song;

import java.util.List;

public class GetPlaylistResponse extends BasicResponse{
    private List<Song> playlist;

    public GetPlaylistResponse(List<Song> playlist) {
        this.playlist = playlist;
    }

    public GetPlaylistResponse(boolean success, Integer errorCode, List<Song> playlist) {
        super(success, errorCode);
        this.playlist = playlist;
    }

    public List<Song> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Song> playlist) {
        this.playlist = playlist;
    }
}
