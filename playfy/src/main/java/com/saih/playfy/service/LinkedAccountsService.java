package com.saih.playfy.service;

import com.saih.playfy.entity.LinkedAccount;
import com.saih.playfy.repository.LinkedAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkedAccountsService {

    @Autowired
    private LinkedAccountsRepository linkedAccountsRepository;

    public List<LinkedAccount> getAllLinkedAccountsForUser(String userId){
        return linkedAccountsRepository.findByUserId(userId);
    }
}
