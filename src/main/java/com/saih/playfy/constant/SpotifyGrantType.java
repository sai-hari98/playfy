package com.saih.playfy.constant;

public enum SpotifyGrantType {

    ACCESS_TOKEN("accessToken"),
    REFRESH_TOKEN("refreshToken");

    private String grantType;

    private SpotifyGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getGrantType() {
        return this.grantType;
    }
}
