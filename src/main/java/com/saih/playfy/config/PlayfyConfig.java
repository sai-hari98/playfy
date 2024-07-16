package com.saih.playfy.config;

import com.saih.playfy.spotify.config.SpotifyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class PlayfyConfig {

    @Autowired
    private SpotifyProperties spotifyProperties;

    @Bean("spotifyRestTemplate")
    public RestTemplate initializeSpotifyRestTemplate(){
        RestTemplate template = new RestTemplate();
        template.setUriTemplateHandler(new DefaultUriBuilderFactory(spotifyProperties.getApiDomain()));
        return template;
    }

    @Bean("spotifyAuthRestTemplate")
    public RestTemplate initializeSpotifyAuthRestTemplate(){
        RestTemplate template = new RestTemplate();
        template.setUriTemplateHandler(new DefaultUriBuilderFactory(spotifyProperties.getTokenUrl()));
        return template;
    }

    @Bean("restTemplate")
    public RestTemplate initializeRestTemplate(){
        return new RestTemplate();
    }
}
