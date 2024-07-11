package com.saih.playfy.entity;

import com.saih.playfy.dto.SpotifyAuthResponse;
import com.saih.playfy.service.SpotifyAuthService;
import lombok.Data;

import java.util.Date;

public class SpotifyToken {
    private String userId;
    private String authToken;
    private String accessToken;
    private String refreshToken;
    private Date expiry;
    private SpotifyAuthService spotifyAuthService;

    public SpotifyToken(String userId, String accessToken, SpotifyAuthService spotifyAuthService){
        this.userId = userId;
        this.accessToken = accessToken;
        this.spotifyAuthService = spotifyAuthService;
    }

    public String getAuthToken(){
        return this.authToken;
    }

    public String getToken() {
        Date now = new Date();
        if(this.accessToken == null){
            this.getAccessToken();
        }else if(now.after(this.expiry)){
            this.refreshToken();
        }
        return this.accessToken;
    }

    private void refreshToken(){
        SpotifyAuthResponse authResponse = spotifyAuthService.refreshToken(this);
        this.accessToken = authResponse.getToken();
        this.refreshToken = authResponse.getRefreshToken();
        this.expiry = new Date(System.currentTimeMillis()+authResponse.getExpiry());
    }

    private void getAccessToken(){
        SpotifyAuthResponse authResponse = spotifyAuthService.getAccessToken(this);
        this.accessToken = authResponse.getToken();
        this.refreshToken = authResponse.getRefreshToken();
        this.expiry = new Date(System.currentTimeMillis()+authResponse.getExpiry());
    }
}
