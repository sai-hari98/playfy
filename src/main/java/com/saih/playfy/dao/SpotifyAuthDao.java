package com.saih.playfy.dao;

import com.saih.playfy.exception.RedirectException;
import com.saih.playfy.spotify.config.SpotifyProperties;
import com.saih.playfy.constant.SpotifyGrantType;
import com.saih.playfy.dto.SpotifyAuthResponse;
import com.saih.playfy.entity.SpotifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Component
public class SpotifyAuthDao {

    @Autowired
    @Qualifier("spotifyAuthRestTemplate")
    private RestTemplate spotifyAuthRestTemplate;

    @Autowired
    private SpotifyProperties spotifyProperties;


    @Autowired
    @Qualifier("spotifyTokenRedisTemplate")
    private RedisTemplate<String, SpotifyToken> spotifyTokenRedisTemplate;

    public SpotifyAuthResponse getToken(SpotifyToken spotifyToken, SpotifyGrantType spotifyGrantType) {
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("grant_type",spotifyProperties.getGrantTypes().get(spotifyGrantType.getGrantType()));
            map.add("code", spotifyToken.getCode());
            if(SpotifyGrantType.ACCESS_TOKEN.equals(spotifyGrantType)) {
                map.add("redirect_uri", spotifyProperties.getRedirectUri());
                headers.add("Authorization", "Basic "+ getEncodedClientIDAndSecret());
            }
            if(SpotifyGrantType.REFRESH_TOKEN.equals(spotifyGrantType)) {
                map.add("client_id", spotifyProperties.getClientId());
            }

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

            ResponseEntity<SpotifyAuthResponse> authResponseEntity = spotifyAuthRestTemplate.exchange("/api/token", HttpMethod.POST, entity, SpotifyAuthResponse.class);
            return authResponseEntity.getBody();
        }catch(HttpClientErrorException httpClientErrorException){
            if(httpClientErrorException.getStatusCode().equals(HttpStatus.BAD_REQUEST)
                && httpClientErrorException.getResponseBodyAsString().contains("Authorization code expired")){
                throw new RedirectException(String.join("/",spotifyProperties.getTokenUrl(),"authorize"));
            }
            throw httpClientErrorException;
        }
    }

    public SpotifyToken getTokenFromCache(String key){
        return spotifyTokenRedisTemplate.opsForValue().get(key);
    }

    private String getEncodedClientIDAndSecret(){
        String clientIdAndSecretJoined = String.join(":",spotifyProperties.getClientId(), spotifyProperties.getClientSecret());
        return Base64.getEncoder().encodeToString(clientIdAndSecretJoined.getBytes());
    }
}
