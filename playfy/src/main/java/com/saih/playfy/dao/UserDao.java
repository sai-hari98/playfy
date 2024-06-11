package com.saih.playfy.dao;

import com.saih.playfy.entity.User;
import com.saih.playfy.exception.BusinessException;
import com.saih.playfy.repository.UserRepository;
import com.saih.playfy.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        try{
            user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
            userRepository.save(user);
        }catch(Exception e){
            log.error("Exception while creating user", e);
            throw new BusinessException("Sorry, we're unable to register you. Please try again");
        }
    }

}
