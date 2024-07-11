package com.saih.playfy.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LinkedAccountSaveDto implements Serializable {

    private String token;
    private String identifier;
}
