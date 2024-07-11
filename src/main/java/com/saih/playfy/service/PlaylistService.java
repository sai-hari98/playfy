package com.saih.playfy.service;

import com.saih.playfy.entity.Playlist;
import com.saih.playfy.repository.PlaylistRepository;
import com.saih.playfy.util.PlayfyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private SpotifyPlaylistService spotifyPlaylistService;

    public List<Playlist> getAllPlaylistsForUser(String userId) {
        return playlistRepository.findByUserId(userId);
    }

    public List<Playlist> syncPlaylists() {
        return spotifyPlaylistService.getPlaylists();
    }
}
