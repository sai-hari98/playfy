package com.saih.playfy.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("playlists")
public class Playlist {

    @Id
    private String playlistId;
    private String userId;
    private String thumbnailUrl;
}
