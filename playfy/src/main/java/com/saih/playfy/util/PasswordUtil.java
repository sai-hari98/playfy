package com.saih.playfy.util;

import com.saih.playfy.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    private static final Logger log = LoggerFactory.getLogger(PasswordUtil.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String hashPassword(String encodedPassword) {
        try{
            return passwordEncoder.encode(encodedPassword);
        }catch(Exception exception){
            log.error("Error occurred while hashing password from encoded password", exception);
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry! We're unable to process your request. Please try again after sometime.");
        }
    }

}
