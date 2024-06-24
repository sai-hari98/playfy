package com.saih.playfy.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spotify")
public class SpotifyProperties {

    @Value("{client-id}")
    private String clientId;

    @Value("{clientsecret}")
    private String clientSecret;

    @Value("{token-url}")
    private String tokenUrl;

    @Value("{scope}")
    private String scope;
}
