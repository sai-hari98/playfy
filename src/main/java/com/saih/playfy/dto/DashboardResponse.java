package com.saih.playfy.dto;

import com.saih.playfy.entity.LinkedAccount;
import com.saih.playfy.entity.Playlist;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class DashboardResponse {

    private List<Playlist> playlists;
    private List<LinkedAccount> linkedAccounts;
}
