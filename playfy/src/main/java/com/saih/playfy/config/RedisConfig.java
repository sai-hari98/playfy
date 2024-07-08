package com.saih.playfy.config;

import com.saih.playfy.entity.LinkedAccount;
import com.saih.playfy.entity.SpotifyToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Bean
    RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisHost, redisPort);
    }

    @Bean
    @Qualifier("linkedAccountRedisTemplate")
    public RedisTemplate<String, LinkedAccount> linkedAccountRedisTemplate() {
        RedisTemplate<String, LinkedAccount> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }

    @Bean
    @Qualifier("spotifyTokenRedisTemplate")
    public RedisTemplate<String, SpotifyToken> spotifyTokenRedisTemplate() {
        RedisTemplate<String, SpotifyToken> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }


}