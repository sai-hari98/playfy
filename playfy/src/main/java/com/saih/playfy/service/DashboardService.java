package com.saih.playfy.service;

import com.saih.playfy.dto.DashboardResponse;
import com.saih.playfy.entity.LinkedAccount;
import com.saih.playfy.entity.Playlist;
import com.saih.playfy.util.PlayfyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private LinkedAccountsService linkedAccountsService;

    public DashboardResponse getDashboard(){
        String userId = PlayfyUtils.getLoggedInUserId();
        List<Playlist> playlists = playlistService.getAllPlaylistsForUser(userId);
        List<LinkedAccount> linkedAccounts = linkedAccountsService.getAllLinkedAccountsForUser(userId);
        return DashboardResponse.builder().playlists(playlists).linkedAccounts(linkedAccounts).build();
    }
}
