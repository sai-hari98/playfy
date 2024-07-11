package com.saih.playfy.controller;

import com.saih.playfy.dto.ErrorResponse;
import com.saih.playfy.exception.BusinessException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class PlayfyControllerAdvice {

    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException businessException){
        return new ResponseEntity<>(new ErrorResponse(Collections.singletonList(businessException.getMessage())), businessException.getHttpStatus());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        List<String> errorMessages = new ArrayList<>();
        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        for(FieldError fieldError : fieldErrors)
            errorMessages.add(fieldError.getDefaultMessage());
        return new ResponseEntity<>(new ErrorResponse(errorMessages), HttpStatus.BAD_REQUEST);
    }

    /*@ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponse> handleGenericExceptions(Exception exception){
        log.error("Exception is", exception);
        return new ResponseEntity<>(new ErrorResponse(Collections.singletonList("Sorry, an error occurred. Please try after sometime.")), HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
}
