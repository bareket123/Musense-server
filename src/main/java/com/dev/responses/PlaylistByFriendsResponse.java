package com.dev.responses;

import com.dev.objects.Song;

import java.util.List;

public class PlaylistByFriendsResponse extends BasicResponse{
  private List<Song> playlistByFriends;

    public PlaylistByFriendsResponse(List<Song> playlistByFriends) {
        this.playlistByFriends = playlistByFriends;
    }

    public PlaylistByFriendsResponse(boolean success, Integer errorCode, List<Song> playlistByFriends) {
        super(success, errorCode);
        this.playlistByFriends = playlistByFriends;
    }

    public List<Song> getPlaylistByFriends() {
        return playlistByFriends;
    }

    public void setPlaylistByFriends(List<Song> playlistByFriends) {
        this.playlistByFriends = playlistByFriends;
    }
}
