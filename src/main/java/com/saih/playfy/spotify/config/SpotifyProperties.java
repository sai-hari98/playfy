package com.saih.playfy.spotify.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "spotify")
public class SpotifyProperties {

    private String clientId;

    private String clientSecret;

    private String tokenUrl;

    private String scope;

    private String redirectUri;

    private Map<String, String> grantTypes;

    private int playlistLimit;

    private String apiDomain;

    private String playlistGetUrl;
}
