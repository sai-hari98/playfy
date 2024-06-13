package com.saih.playfy.service;

import com.saih.playfy.dao.UserDao;
import com.saih.playfy.dto.LoginRequest;
import com.saih.playfy.entity.User;
import com.saih.playfy.exception.BusinessException;
import com.saih.playfy.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserDao userDao;

    public boolean login(LoginRequest loginRequest){
        User user = userDao.getUser(loginRequest.getUserId());
        String hashedPassword = PasswordUtil.hashPasswordFromBase64EncodedPassword(loginRequest.getPassword());
        if(!user.getPassword().equals(hashedPassword)){
            throw new BusinessException(HttpStatus.UNAUTHORIZED, "Invalid Password");
        }
        return true;
    }
}
