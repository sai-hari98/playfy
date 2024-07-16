package com.saih.playfy.exception;

import lombok.Getter;

@Getter
public class RedirectException extends RuntimeException{

    private final String redirectUrl;

    public RedirectException(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

}
