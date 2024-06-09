package com.saih.playfy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class PlayfyConfig {

    @Bean("spotifyRestTemplate")
    public RestTemplate initializeRestTemplate(){
        RestTemplate template = new RestTemplate();
        template.setUriTemplateHandler(new DefaultUriBuilderFactory("https://api.spotify.com"));
        return template;
    }
}
