package com.saih.playfy.dto;

import com.saih.playfy.entity.LinkedAccount;
import lombok.Data;

@Data
public class LinkedAccountsResponse {
    private LinkedAccount spotify;
    private LinkedAccount primeMusic;
    private LinkedAccount ytMusic;
}
