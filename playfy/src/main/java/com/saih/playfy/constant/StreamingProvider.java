package com.saih.playfy.constant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum StreamingProvider {

    @JsonProperty("spotify")
    SPOTIFY("spotify"),
    @JsonProperty("primemusic")
    PRIME_MUSIC("primemusic"),
    @JsonProperty("ytmusic")
    YT_MUSIC("ytmusic");

    private final String streamingProvider;

    StreamingProvider(String streamingProvider){
        this.streamingProvider = streamingProvider;
    }

    @JsonValue
    public String getStreamingProvider(){
        return this.streamingProvider;
    }

}
