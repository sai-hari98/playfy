package com.saih.playfy.service;

import com.saih.playfy.config.PlayfyProperties;
import com.saih.playfy.dao.UserDao;
import com.saih.playfy.dto.LoginRequest;
import com.saih.playfy.entity.User;
import com.saih.playfy.exception.BusinessException;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PlayfyProperties playfyProperties;

    public Cookie login(LoginRequest loginRequest){
        User user = userDao.getUser(loginRequest.getUserId());
        if(!bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new BusinessException(HttpStatus.UNAUTHORIZED, "Invalid Password");
        }

        Cookie cookie = new Cookie("token", jwtService.generateToken(user));
        cookie.setHttpOnly(false);
        cookie.setMaxAge(playfyProperties.getExpiry());
        cookie.setPath("/");
        return cookie;
    }
}
