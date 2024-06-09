package com.saih.playfy.dao;

import com.saih.playfy.config.PlayfyProperties;
import com.saih.playfy.entity.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthDao {

    @Autowired
    @Qualifier("spotifyRestTemplate")
    private RestTemplate spotifyRestTemplate;

    @Autowired
    private PlayfyProperties playfyProperties;

    public AuthResponse getSpotifyAuthentication(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type","client_credentials");
        map.add("client_id", playfyProperties.getClientId());
        map.add("client_secret", playfyProperties.getClientId());

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<AuthResponse> authResponseEntity = spotifyRestTemplate.exchange("/api/token", HttpMethod.POST, entity, AuthResponse.class);
        return authResponseEntity.getBody();
    }
}
