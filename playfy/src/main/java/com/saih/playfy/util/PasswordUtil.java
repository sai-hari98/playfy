package com.saih.playfy.util;

import com.saih.playfy.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.security.MessageDigest;
import java.util.Base64;

public class PasswordUtil {

    private static final Logger log = LoggerFactory.getLogger(PasswordUtil.class);

    public static String hashPasswordFromBase64EncodedPassword(String encodedPassword) {
        try{
            byte[] passwordBytes = Base64.getDecoder().decode(encodedPassword);
            final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            final byte[] hashbytes = digest.digest(
                    passwordBytes);
            return bytesToHex(hashbytes);
        }catch(Exception exception){
            log.error("Error occurred while hashing password from encoded password", exception);
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry! We're unable to process your request. Please try again after sometime.");
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
