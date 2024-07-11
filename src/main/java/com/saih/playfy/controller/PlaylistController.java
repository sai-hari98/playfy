package com.saih.playfy.controller;

import com.saih.playfy.entity.Playlist;
import com.saih.playfy.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/sync")
    public List<Playlist> syncPlaylists() {
        return playlistService.syncPlaylists();
    }
}
