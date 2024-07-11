package com.saih.playfy.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class PlayfyProperties {

    @Value("${security.jwt.expiration-time}")
    private int expiry;
}
