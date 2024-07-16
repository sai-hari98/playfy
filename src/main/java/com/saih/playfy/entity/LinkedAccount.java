package com.saih.playfy.entity;

import com.saih.playfy.constant.StreamingProvider;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("linkedaccounts")
public class LinkedAccount implements Serializable {

    @NotEmpty(message = "Please provide a valid user id")
    private String userId;
    @NotNull(message = "Please provide a valid streaming provider")
    private StreamingProvider provider;
    private String token;
    private String accountId;

    public LinkedAccount() {}

    public LinkedAccount(String userId, StreamingProvider streamingProvider){
        this.userId = userId;
        this.provider = streamingProvider;
    }
}
