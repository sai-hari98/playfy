package com.saih.playfy.dto;

public class Error {

    private String code;
    private final String message;

    public Error(String message){
        this.message = message;
    }

    public Error(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
