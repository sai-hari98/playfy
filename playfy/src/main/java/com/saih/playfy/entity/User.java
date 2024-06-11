package com.saih.playfy.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
@Data
public class User {

    @Id
    private String userId;
    private String password;
    private String spotifyId;
}
