package com.saih.playfy.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Document("users")
@Data
public class User implements UserDetails {

    @Id
    @NotBlank(message = "User ID cannot be empty")
    private String userId;
    @NotBlank(message = "Password cannot be empty")
    private String password;
    private String spotifyId;
    private String firstName;
    private String lastName;
    private String email;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.userId;
    }
}
