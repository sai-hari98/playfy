package com.saih.playfy.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthResponse {

    @JsonProperty("access_token")
    private String token;
    @JsonProperty("expires_in")
    private long expiry;
    private long responseReceivedTime;
}
