package com.saih.playfy.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class SpotifyToken {
    private final String userId;
    private final String authCode;
    private String accessToken;
    private String refreshToken;
    private Date expiry;
    private long expirySeconds;

    public void setExpiry(long expiry){
        this.expirySeconds = expiry;
        this.expiry = new Date(System.currentTimeMillis() + expirySeconds * 1000);
    }
}
