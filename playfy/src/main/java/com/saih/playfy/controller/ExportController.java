package com.saih.playfy.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/export")
@RestController
public class ExportController {

    @GetMapping
    public void exportPlaylistTracks(HttpServletResponse response) {
        response.setContentType("text/csv");

    }
}
