package com.saih.playfy.service;

import com.saih.playfy.constant.StreamingProvider;
import com.saih.playfy.dao.LinkedAccountsDao;
import com.saih.playfy.dto.LinkedAccountSaveDto;
import com.saih.playfy.dto.LinkedAccountsResponse;
import com.saih.playfy.entity.LinkedAccount;
import com.saih.playfy.repository.LinkedAccountsRepository;
import com.saih.playfy.spotify.config.SpotifyProperties;
import com.saih.playfy.util.PlayfyUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Service
public class LinkedAccountsService {

    @Autowired
    private LinkedAccountsRepository linkedAccountsRepository;

    @Autowired
    private SpotifyProperties spotifyProperties;

    @Autowired
    private LinkedAccountsDao linkedAccountsDao;

    public String linkAccount(LinkedAccount linkedAccount){
        if(linkedAccount.getProvider().equals(StreamingProvider.SPOTIFY)){
            String identifier = UUID.randomUUID().toString();
            linkedAccountsDao.cacheAccountInfoToLink(identifier, linkedAccount);
            return UriComponentsBuilder.fromUriString(spotifyProperties.getTokenUrl())
                    .queryParam("response_type", "code")
                    .queryParam("client_id", encodeUtf8(spotifyProperties.getClientId()))
                    .queryParam("scope", encodeUtf8(spotifyProperties.getScope()))
                    .queryParam("redirect_uri", encodeUtf8("http://localhost:3000/linked-accounts/spotify"))
                    .queryParam("state", encodeUtf8(identifier))
                    .build()
                    .toUriString();
        }
        return null;
    }

    public LinkedAccount getUserLinkedAccountByProvider(StreamingProvider streamingProvider){
        return linkedAccountsDao.getLinkedAccountByProvider(streamingProvider);
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

    private static String encodeUtf8(String val) {
        return URLEncoder.encode(val, StandardCharsets.UTF_8);
    }
}
