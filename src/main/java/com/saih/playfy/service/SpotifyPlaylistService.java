package com.saih.playfy.service;

import com.saih.playfy.dao.SpotifyPlaylistDao;
import com.saih.playfy.entity.Playlist;
import com.saih.playfy.entity.SpotifyToken;
import com.saih.playfy.repository.PlaylistRepository;
import com.saih.playfy.util.PlayfyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class SpotifyPlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private SpotifyPlaylistDao spotifyPlaylistDao;

    @Autowired
    private SpotifyAuthService spotifyAuthService;

    public List<Playlist> getPlaylists() {
        SpotifyToken spotifyToken = spotifyAuthService.getSpotifyToken();
        List<Playlist> existingPlaylists = playlistRepository.findByUserId(PlayfyUtils.getLoggedInUserId());
        List<Playlist> playlistsFromSpotifyAPI = spotifyPlaylistDao.getPlaylists(spotifyToken);
        CompletableFuture.runAsync(() -> updateNewPlaylistsToCollection(existingPlaylists, playlistsFromSpotifyAPI));
        return playlistsFromSpotifyAPI;
    }

    public void updateNewPlaylistsToCollection(List<Playlist> existingPlaylists, List<Playlist> playlistsFromSpotifyAPI){
        List<Playlist> newPlaylistsToUpdate = getNewPlaylists(existingPlaylists, playlistsFromSpotifyAPI);
        playlistRepository.saveAll(newPlaylistsToUpdate);
    }

    public List<Playlist> getNewPlaylists(List<Playlist> oldPlaylists, List<Playlist> allPlaylists) {
        Set<String> oldPlaylistIDs = oldPlaylists.stream().map(Playlist::getPlaylistId).collect(Collectors.toSet());
        return allPlaylists.stream().filter(playlist -> !oldPlaylistIDs.contains(playlist.getPlaylistId())).collect(Collectors.toList());
    }
}
