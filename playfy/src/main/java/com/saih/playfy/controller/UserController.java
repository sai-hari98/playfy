package com.saih.playfy.controller;

import com.saih.playfy.dto.LinkedAccountsResponse;
import com.saih.playfy.entity.LinkedAccount;
import com.saih.playfy.entity.User;
import com.saih.playfy.service.LinkedAccountsService;
import com.saih.playfy.service.UserService;
import com.saih.playfy.util.PlayfyUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LinkedAccountsService linkedAccountsService;

    @PostMapping("/register")
    public ResponseEntity<Boolean> createUser(@Valid @RequestBody User user){
        userService.createUser(user);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @GetMapping("/linked-accounts")
    public LinkedAccountsResponse getLinkedAccounts(){
        return linkedAccountsService.getUserLinkedAccounts();
    }
}
