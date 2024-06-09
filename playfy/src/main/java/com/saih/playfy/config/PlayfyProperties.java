package com.saih.playfy.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties
public class PlayfyProperties {

    @Value("{spotify.clientid}")
    private String clientId;

    @Value("{spotify.clientsecret}")
    private String clientSecret;
}
