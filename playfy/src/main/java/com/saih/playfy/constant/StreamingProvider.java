package com.saih.playfy.constant;

import lombok.Getter;

@Getter
public enum StreamingProvider {

    SPOTIFY("spotify"),
    PRIME_MUSIC("primemusic"),
    YT_MUSIC("ytmusic");

    private final String streamingProvider;

    StreamingProvider(String streamingProvider){
        this.streamingProvider = streamingProvider;
    }

    public String toString(){
        return this.streamingProvider;
    }

}
