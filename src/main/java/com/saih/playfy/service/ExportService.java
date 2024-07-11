package com.saih.playfy.service;

import com.saih.playfy.dao.ExportDao;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExportService {

    @Autowired
    private ExportDao exportDao;

    public void getPlaylistContent(String playlistName, HttpServletResponse httpServletResponse){
        String playlistId = exportDao.getPlaylistId(playlistName);
    }

}
