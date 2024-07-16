package com.saih.playfy.dao;

import com.saih.playfy.exception.RedirectException;
import com.saih.playfy.spotify.config.SpotifyProperties;
import com.saih.playfy.entity.Playlist;
import com.saih.playfy.entity.SpotifyToken;
import com.saih.playfy.exception.BusinessException;
import com.saih.playfy.spotify.entity.SpotifyPlaylist;
import com.saih.playfy.spotify.entity.SpotifyPlaylistResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SpotifyPlaylistDao {

    private static final Logger log = LoggerFactory.getLogger(SpotifyPlaylistDao.class);

    @Qualifier("spotifyRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SpotifyProperties spotifyProperties;

    public List<Playlist> getPlaylists(SpotifyToken spotifyToken){
        try{
            int iteration = 0;
            List<SpotifyPlaylist> spotifyPlaylists = new ArrayList<>();
            while(true){
                int offset = iteration * spotifyProperties.getPlaylistLimit();
                Map<String, Integer> variables = new HashMap<>();
                variables.put("offset", offset);
                variables.put("limit", spotifyProperties.getPlaylistLimit());
                ResponseEntity<SpotifyPlaylistResponse> responseEntity = restTemplate.exchange(spotifyProperties.getPlaylistGetUrl(),
                        HttpMethod.GET,
                        getHttpEntityForHeaders(spotifyToken),
                        SpotifyPlaylistResponse.class,
                        variables);
                SpotifyPlaylistResponse playlistResponse = responseEntity.getBody();
                spotifyPlaylists.addAll(playlistResponse.getItems());
                iteration++;
                if(playlistResponse.getTotal() < iteration * spotifyProperties.getPlaylistLimit()){
                    break;
                }
            }
            return spotifyPlaylists.stream().map(SpotifyPlaylist::toPlaylist).collect(Collectors.toList());
        } catch (RedirectException redirectException) {
            throw redirectException;
        } catch(Exception exception) {
            log.error("Exception occurred while fetching spotify playlists", exception);
            throw new BusinessException("Sorry, we're unable to fetch your spotify playlists. Please try again later");
        }
    }

    private static HttpEntity<String> getHttpEntityForHeaders(SpotifyToken spotifyToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.join(" ", "Bearer", spotifyToken.getToken()));
        return new HttpEntity<>(headers);
    }
}
