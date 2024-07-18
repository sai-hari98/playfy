package com.saih.playfy.entity;

import com.saih.playfy.service.SpotifyAuthService;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
public class SpotifyToken {
    private final String userId;
    private final String authCode;
    private String accessToken;
    private String refreshToken;
    private Date expiry;
    private long expirySeconds;
    private SpotifyAuthService spotifyAuthService;

    public SpotifyToken(String userId, String authCode, SpotifyAuthService spotifyAuthService){
        this.userId = userId;
        this.authCode = authCode;
        this.spotifyAuthService = spotifyAuthService;
    }
}
