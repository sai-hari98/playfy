package com.saih.playfy.spotify.entity;

import lombok.Data;

import java.util.List;

@Data
public class SpotifyPlaylistResponse {

    private int total;
    private List<SpotifyPlaylist> items;
}
