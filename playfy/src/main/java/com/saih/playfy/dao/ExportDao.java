package com.saih.playfy.dao;

import com.saih.playfy.entity.SpotifySearchResponse;
import com.saih.playfy.exception.BusinessException;
import com.saih.playfy.service.SpotifyAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExportDao {

    @Autowired
    @Qualifier("spotifyRestTemplate")
    private RestTemplate spotifyRestTemplate;

    @Autowired
    private SpotifyAuthService spotifyAuthService;

    public String getPlaylistId(String playlistName){
        String url = "/v1/search?q=%s&type=playlist".formatted(playlistName);
        String token = spotifyAuthService.getAuthToken();
        SpotifySearchResponse searchResponse = spotifyRestTemplate.getForObject(url, SpotifySearchResponse.class);
        if(searchResponse != null && searchResponse.getPlaylists() != null && !searchResponse.getPlaylists().isEmpty()
                && searchResponse.getPlaylists().getFirst().getName().equalsIgnoreCase(playlistName)){
            return searchResponse.getPlaylists().getFirst().getId();
        }else{
            throw new BusinessException("Spotify Playlist does not exist");
        }
    }

}
