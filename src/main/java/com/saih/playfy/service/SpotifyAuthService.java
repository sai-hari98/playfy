package com.saih.playfy.service;

import com.saih.playfy.constant.SpotifyGrantType;
import com.saih.playfy.constant.StreamingProvider;
import com.saih.playfy.dao.LinkedAccountsDao;
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
    private LinkedAccountsDao linkedAccountsDao;

    private SpotifyToken getAccessToken(SpotifyToken spotifyToken){
        SpotifyAuthResponse spotifyAuthResponse = spotifyAuthDao.getToken(spotifyToken, SpotifyGrantType.ACCESS_TOKEN);
        spotifyToken.setAccessToken(spotifyAuthResponse.getToken());
        spotifyToken.setRefreshToken(spotifyAuthResponse.getRefreshToken());
        spotifyToken.setExpiry(spotifyAuthResponse.getExpiry());
        return spotifyToken;
    }

    public SpotifyToken refreshToken(SpotifyToken spotifyToken){
        SpotifyAuthResponse spotifyAuthResponse = spotifyAuthDao.getToken(spotifyToken, SpotifyGrantType.REFRESH_TOKEN);
        spotifyToken.setAccessToken(spotifyAuthResponse.getToken());
        spotifyToken.setRefreshToken(spotifyAuthResponse.getRefreshToken());
        spotifyToken.setExpiry(spotifyAuthResponse.getExpiry());
        return spotifyToken;
    }

    public SpotifyToken getSpotifyToken(){
        SpotifyToken token = spotifyAuthDao.getTokenFromCache(getSpotifyTokenRedisKey());
        Date now = new Date();
        if(token == null) {
            token = initToken();
        } else if(now.after(token.getExpiry())){
            refreshToken(token);
            LinkedAccount linkedAccount = linkedAccountsDao.getUserLinkedAccountByProvider(StreamingProvider.SPOTIFY);
            linkedAccount.setRefreshToken(token.getRefreshToken());
            linkedAccountsDao.saveLinkedAccount(linkedAccount);
        }
        return token;
    }

    private SpotifyToken initToken(){
        LinkedAccount linkedAccount = linkedAccountsDao.getUserLinkedAccountByProvider(StreamingProvider.SPOTIFY);
        SpotifyToken spotifyToken = SpotifyToken.builder()
                .userId(linkedAccount.getUserId())
                .refreshToken(linkedAccount.getRefreshToken())
                .build();
        SpotifyToken updatedToken = refreshToken(spotifyToken);
        linkedAccount.setRefreshToken(updatedToken.getRefreshToken());
        linkedAccountsDao.updateRefreshToken(linkedAccount);
        return spotifyToken;
    }

    public SpotifyToken initToken(LinkedAccount linkedAccount){
        SpotifyToken spotifyToken = SpotifyToken.builder()
                .userId(linkedAccount.getUserId())
                .authCode(linkedAccount.getAuthCode())
                .build();
        SpotifyToken updatedToken = getAccessToken(spotifyToken);
        spotifyAuthDao.cacheSpotifyToken(getSpotifyTokenRedisKey(), updatedToken);
        return updatedToken;
    }

    private String getSpotifyTokenRedisKey(){
        return String.join("-","spotify","token",PlayfyUtils.getLoggedInUserId());
    }
}
