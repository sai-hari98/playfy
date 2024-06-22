package com.saih.playfy.service;

import com.saih.playfy.dto.LinkedAccountsResponse;
import com.saih.playfy.entity.LinkedAccount;
import com.saih.playfy.repository.LinkedAccountsRepository;
import com.saih.playfy.util.PlayfyUtils;
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

    public LinkedAccountsResponse getUserLinkedAccounts(){
        String userId = PlayfyUtils.getLoggedInUserId();
        List<LinkedAccount> linkedAccounts = getAllLinkedAccountsForUser(userId);
        LinkedAccountsResponse linkedAccountsResponse = new LinkedAccountsResponse();
        for(LinkedAccount linkedAccount : linkedAccounts){
            switch (linkedAccount.getProvider()){
                case SPOTIFY -> linkedAccountsResponse.setSpotify(linkedAccount);
                case PRIME_MUSIC -> linkedAccountsResponse.setPrimeMusic(linkedAccount);
                case YT_MUSIC -> linkedAccountsResponse.setYtMusic(linkedAccount);
            }
        }
        return linkedAccountsResponse;
    }
}
