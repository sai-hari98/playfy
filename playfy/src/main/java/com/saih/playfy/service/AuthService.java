package com.saih.playfy.service;

import com.saih.playfy.dao.AuthDao;
import com.saih.playfy.entity.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthDao authDao;

    private static AuthResponse authResponse;

    public String getAuthToken(){
        if(authResponse == null || (System.currentTimeMillis() - authResponse.getResponseReceivedTime() > authResponse.getExpiry())){
            authResponse = authDao.getSpotifyAuthentication();
        }

        return authResponse.getToken();
    }
}
