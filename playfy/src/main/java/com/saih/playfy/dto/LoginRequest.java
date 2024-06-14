package com.saih.playfy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "User ID cannot be empty")
    private String userId;
    @NotBlank(message = "Password cannot be empty")
    private String password;
}
