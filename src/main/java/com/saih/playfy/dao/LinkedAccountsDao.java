package com.saih.playfy.dao;

import com.saih.playfy.constant.StreamingProvider;
import com.saih.playfy.entity.LinkedAccount;
import com.saih.playfy.repository.LinkedAccountsRepository;
import com.saih.playfy.util.PlayfyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class LinkedAccountsDao {

    @Autowired
    @Qualifier("linkedAccountRedisTemplate")
    private RedisTemplate<String, LinkedAccount> redisTemplate;

    @Autowired
    private LinkedAccountsRepository linkedAccountsRepository;

    public void cacheAccountInfoToLink(String identifier, LinkedAccount linkedAccount){
        redisTemplate.opsForValue().set(identifier, linkedAccount);
    }

    public LinkedAccount getAccountInfoFromCache(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void removeAccountInfoFromCache(String key){
        redisTemplate.delete(key);
    }

    public LinkedAccount getLinkedAccountByProvider(StreamingProvider streamingProvider) {
        return linkedAccountsRepository.findByUserIdAndProvider(PlayfyUtils.getLoggedInUserId(), streamingProvider).getFirst();
    }
}
