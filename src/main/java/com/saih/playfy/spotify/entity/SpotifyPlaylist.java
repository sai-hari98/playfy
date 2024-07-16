package com.saih.playfy.spotify.entity;

import com.saih.playfy.constant.StreamingProvider;
import com.saih.playfy.entity.Playlist;
import lombok.Data;

import java.util.List;

@Data
public class SpotifyPlaylist {
    private String id;
    private List<SpotifyImage> images;
    private SpotifyOwner owner;
    private String name;

    public Playlist toPlaylist() {
        String owner = this.owner.getDisplayName();
        String thumbnailUrl = images.getFirst().getUrl();
        String id = this.id;
        return new Playlist(id, StreamingProvider.SPOTIFY, owner, thumbnailUrl, name);
    }
}
