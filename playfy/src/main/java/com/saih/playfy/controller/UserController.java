package com.saih.playfy.controller;

import com.saih.playfy.entity.User;
import com.saih.playfy.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Boolean> createUser(@Valid @RequestBody User user){
        userService.createUser(user);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }
}
