package com.saih.playfy.service;

import com.saih.playfy.config.SpotifyProperties;
import com.saih.playfy.dao.SpotifyAuthDao;
import com.saih.playfy.entity.AuthResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class SpotifyAuthService {

    @Autowired
    private SpotifyAuthDao spotifyAuthDao;

    @Autowired
    private SpotifyProperties spotifyProperties;

    private static AuthResponse authResponse;

    public String getAuthToken(){
        if(authResponse == null || (System.currentTimeMillis() - authResponse.getResponseReceivedTime() > authResponse.getExpiry())){
            authResponse = spotifyAuthDao.getSpotifyAuthentication();
        }

        return authResponse.getToken();
    }

    public String getSpotifyRedirectUrlToLink(String identifier){
        return UriComponentsBuilder.fromUriString(spotifyProperties.getTokenUrl())
                .queryParam("response_type", "code")
                .queryParam("client_id", encodeUtf8(spotifyProperties.getClientId()))
                .queryParam("scope", encodeUtf8(spotifyProperties.getScope()))
                .queryParam("redirect_uri", encodeUtf8("http://localhost:3000/linked-accounts/spotify"))
                .queryParam("state", encodeUtf8(identifier))
                .build()
                .toUriString();
    }

    private static String encodeUtf8(String val) {
        return URLEncoder.encode(val, StandardCharsets.UTF_8);
    }
}
