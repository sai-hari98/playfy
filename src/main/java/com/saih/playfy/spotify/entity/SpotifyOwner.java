package com.saih.playfy.spotify.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpotifyOwner {
    @JsonProperty("display_name")
    private String displayName;
    private String id;
}
