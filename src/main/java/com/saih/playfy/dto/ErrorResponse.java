package com.saih.playfy.dto;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {
    private List<Error> errors;

    public ErrorResponse(List<Error> errors){
        this.errors = errors;
    }
}
