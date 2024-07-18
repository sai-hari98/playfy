package com.saih.playfy.service;

import com.saih.playfy.constant.SpotifyGrantType;
import com.saih.playfy.constant.StreamingProvider;
import com.saih.playfy.dao.SpotifyAuthDao;
import com.saih.playfy.dto.SpotifyAuthResponse;
import com.saih.playfy.entity.LinkedAccount;
import com.saih.playfy.entity.SpotifyToken;
import com.saih.playfy.util.PlayfyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SpotifyAuthService {

    @Autowired
    private SpotifyAuthDao spotifyAuthDao;

    @Autowired
    private LinkedAccountsService linkedAccountsService;

    private SpotifyAuthResponse getAccessToken(SpotifyToken spotifyToken){
        return spotifyAuthDao.getToken(spotifyToken, SpotifyGrantType.ACCESS_TOKEN);
    }

    public SpotifyAuthResponse refreshToken(SpotifyToken spotifyToken){
        return spotifyAuthDao.getToken(spotifyToken, SpotifyGrantType.REFRESH_TOKEN);
    }

    public SpotifyToken getSpotifyToken(){
        SpotifyToken token = spotifyAuthDao.getTokenFromCache(getSpotifyTokenRedisKey());
        if(token == null) {
            token = initToken();
        }
        return token;
    }

    private SpotifyToken initToken(){
        LinkedAccount linkedAccount = linkedAccountsService.getUserLinkedAccountByProvider(StreamingProvider.SPOTIFY);
        SpotifyToken spotifyToken = new SpotifyToken(linkedAccount.getUserId(), linkedAccount.getAuthCode(), this);
        //TODO: get new access token and refresh token from the refresh token in DB
        return spotifyToken;
    }

    public SpotifyToken initToken(LinkedAccount linkedAccount){
        SpotifyToken spotifyToken = new SpotifyToken(linkedAccount.getUserId(), linkedAccount.getAuthCode(), this);
        SpotifyAuthResponse spotifyAuthResponse = getAccessToken(spotifyToken);
        spotifyToken.setAccessToken(spotifyAuthResponse.getToken());
        spotifyToken.setRefreshToken(spotifyAuthResponse.getRefreshToken());
        spotifyAuthDao.cacheSpotifyToken(getSpotifyTokenRedisKey(), spotifyToken);
        return spotifyToken;
    }

/*    public String getToken() {
        SpotifyToken spotifyToken = spotifyAuthDao.getTokenFromCache()
        Date now = new Date();
        if(this.accessToken == null){
            this.getAccessToken();
        }else if(now.after(this.expiry)){
            this.refreshToken();
        }
        return this.accessToken;
    }

    private void refreshToken(){
        SpotifyAuthResponse authResponse = refreshToken(this);
        this.accessToken = authResponse.getToken();
        this.refreshToken = authResponse.getRefreshToken();
        this.expirySeconds = authResponse.getExpiry();
        this.expiry = new Date(System.currentTimeMillis() + (this.expirySeconds * 1000));
    }

    private void getAccessToken(){
        SpotifyAuthResponse authResponse = spotifyAuthService.getAccessToken(this);
        this.accessToken = authResponse.getToken();
        this.expirySeconds = authResponse.getExpiry();
        this.expiry = new Date(System.currentTimeMillis() + (this.expirySeconds * 1000));
    }*/

    private String getSpotifyTokenRedisKey(){
        return String.join("-","spotify","token",PlayfyUtils.getLoggedInUserId());
    }
}
