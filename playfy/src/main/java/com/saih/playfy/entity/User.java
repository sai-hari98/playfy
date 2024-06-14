package com.saih.playfy.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
@Data
public class User {

    @Id
    @NotBlank(message = "User ID cannot be empty")
    private String userId;
    @NotBlank(message = "Password cannot be empty")
    private String password;
    private String spotifyId;
}
