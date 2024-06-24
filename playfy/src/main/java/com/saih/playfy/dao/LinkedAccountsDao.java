package com.saih.playfy.dao;

import com.saih.playfy.entity.LinkedAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class LinkedAccountsDao {

    @Autowired
    @Qualifier("linkedAccountRedisTemplate")
    private RedisTemplate<String, LinkedAccount> redisTemplate;

    public void cacheAccountInfoToLink(String identifier, LinkedAccount linkedAccount){
        redisTemplate.opsForValue().set(identifier, linkedAccount);
    }

    public LinkedAccount getAccountInfoFromCache(String key){
        return redisTemplate.opsForValue().get(key);
    }
}
