package com.saih.playfy.service;

import com.saih.playfy.dao.UserDao;
import com.saih.playfy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void createUser(User user){
        userDao.createUser(user);
    }

}
