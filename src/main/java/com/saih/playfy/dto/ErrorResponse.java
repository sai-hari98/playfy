package com.saih.playfy.dto;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {
    private List<String> errorMessages;

    public ErrorResponse(List<String> errorMessages){
        this.errorMessages = errorMessages;
    }
}
