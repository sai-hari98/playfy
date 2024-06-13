package com.saih.playfy.controller;

import com.saih.playfy.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class PlayfyControllerAdvice {

    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<Object> handleBusinessException(BusinessException businessException){
        Map<String, String> errorResponseBody = new HashMap<>();
        errorResponseBody.put("errorMessage",businessException.getMessage());
        return new ResponseEntity<>(errorResponseBody, businessException.getHttpStatus());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleGenericExceptions(){
        Map<String, String> errorResponseBody = new HashMap<>();
        errorResponseBody.put("errorMessage", "Sorry, an error occurred. Please try after sometime.");
        return new ResponseEntity<>(errorResponseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
