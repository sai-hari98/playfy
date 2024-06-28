package com.saih.playfy.service;

import com.saih.playfy.constant.StreamingProvider;
import com.saih.playfy.dao.LinkedAccountsDao;
import com.saih.playfy.dto.LinkedAccountSaveDto;
import com.saih.playfy.dto.LinkedAccountsResponse;
import com.saih.playfy.entity.LinkedAccount;
import com.saih.playfy.repository.LinkedAccountsRepository;
import com.saih.playfy.util.PlayfyUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Service
public class LinkedAccountsService {

    @Autowired
    private LinkedAccountsRepository linkedAccountsRepository;

    @Autowired
    private SpotifyAuthService spotifyAuthService;

    @Autowired
    private LinkedAccountsDao linkedAccountsDao;

    public String linkAccount(LinkedAccount linkedAccount){
        if(linkedAccount.getProvider().equals(StreamingProvider.SPOTIFY)){
            String identifier = UUID.randomUUID().toString();
            linkedAccountsDao.cacheAccountInfoToLink(identifier, linkedAccount);
            return spotifyAuthService.getSpotifyRedirectUrlToLink(identifier);
        }
        return null;
    }
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

    public void saveAccount(LinkedAccountSaveDto linkedAccountSaveDto) {
        LinkedAccount linkedAccount = linkedAccountsDao.getAccountInfoFromCache(URLDecoder.decode(linkedAccountSaveDto.getIdentifier(), StandardCharsets.UTF_8));
        linkedAccount.setToken(linkedAccountSaveDto.getToken());
        linkedAccountsRepository.save(linkedAccount);
    }
}
