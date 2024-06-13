package com.saih.playfy.service;

import com.saih.playfy.dao.SpotifyAuthDao;
import com.saih.playfy.entity.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpotifyAuthService {

    @Autowired
    private SpotifyAuthDao spotifyAuthDao;

    private static AuthResponse authResponse;

    public String getAuthToken(){
        if(authResponse == null || (System.currentTimeMillis() - authResponse.getResponseReceivedTime() > authResponse.getExpiry())){
            authResponse = spotifyAuthDao.getSpotifyAuthentication();
        }

        return authResponse.getToken();
    }
}
