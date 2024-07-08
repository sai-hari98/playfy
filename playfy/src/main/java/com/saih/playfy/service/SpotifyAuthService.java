package com.saih.playfy.service;

import com.saih.playfy.spotify.config.SpotifyProperties;
import com.saih.playfy.constant.SpotifyGrantType;
import com.saih.playfy.constant.StreamingProvider;
import com.saih.playfy.dao.SpotifyAuthDao;
import com.saih.playfy.dto.SpotifyAuthResponse;
import com.saih.playfy.entity.LinkedAccount;
import com.saih.playfy.entity.SpotifyToken;
import com.saih.playfy.util.PlayfyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class SpotifyAuthService {

    @Autowired
    private SpotifyAuthDao spotifyAuthDao;

    @Autowired
    private LinkedAccountsService linkedAccountsService;

    public SpotifyAuthResponse getAccessToken(SpotifyToken spotifyToken){
        return spotifyAuthDao.getToken(spotifyToken, SpotifyGrantType.ACCESS_TOKEN);
    }

    public SpotifyAuthResponse refreshToken(SpotifyToken spotifyToken){
        return spotifyAuthDao.getToken(spotifyToken, SpotifyGrantType.REFRESH_TOKEN);
    }

    public SpotifyToken getSpotifyToken(){
        String userId = PlayfyUtils.getLoggedInUserId();
        String tokenKey = String.join("","spotify-token-",userId);
        SpotifyToken token = spotifyAuthDao.getTokenFromCache(tokenKey);
        if(token == null) {
            token = initToken();
        }
        return token;
    }

    private SpotifyToken initToken(){
        LinkedAccount linkedAccount = linkedAccountsService.getUserLinkedAccountByProvider(StreamingProvider.SPOTIFY);
        SpotifyToken spotifyToken = new SpotifyToken(linkedAccount.getUserId(), linkedAccount.getToken(), this);
        return spotifyToken;
    }


}
