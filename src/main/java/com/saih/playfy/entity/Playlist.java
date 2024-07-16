package com.saih.playfy.entity;

import com.saih.playfy.constant.StreamingProvider;
import com.saih.playfy.util.PlayfyUtils;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("playlists")
public class Playlist {

    @Id
    private String playlistId;
    private StreamingProvider provider;
    private String userId;
    private String createdBy;
    private String thumbnailUrl;
    private String title;

    public Playlist(String playlistId, StreamingProvider provider, String createdBy, String thumbnailUrl, String title){
        this.playlistId = playlistId;
        this.provider = provider;
        this.createdBy = createdBy;
        this.thumbnailUrl = thumbnailUrl;
        this.userId = PlayfyUtils.getLoggedInUserId();
        this.title = title;
    }
}
