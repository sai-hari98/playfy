package com.saih.playfy.entity;

import com.saih.playfy.constant.StreamingProvider;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("linkedaccounts")
public class LinkedAccount {

    private String userId;
    private String playlistId;
    private StreamingProvider provider;
    private String token;
    private String accountId;
}
