package com.saih.playfy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpotifyAuthResponse {

    @JsonProperty("access_token")
    private String token;
    @JsonProperty("expires_in")
    private long expiry;
    @JsonProperty("refresh_token")
    private String refreshToken;
}
