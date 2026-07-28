package com.yt.tools.controller;

import com.yt.tools.Service.Ytservice;
import com.yt.tools.models.SearchVideo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/youtube")
@RequiredArgsConstructor
public class YoutubeTagController {

    @Value("${youtube.api.key}")
    private String apiKey;

    private final Ytservice ytservice;

    private boolean isApiKeyConfigured() {
        return apiKey != null && !apiKey.isBlank();
    }

    @PostMapping("/search")
    public String videoTags(@RequestParam("videoTitle") String videoTitle,
                            Model model) {

        if (!isApiKeyConfigured()) {
            model.addAttribute("error", "API key is not configured.");
            return "home";
        }

        if (videoTitle == null || videoTitle.isBlank()) {
            model.addAttribute("error", "YouTube video title is required.");
            return "home";
        }

        try {

            SearchVideo result = ytservice.searchVideos(videoTitle);

            model.addAttribute("primaryVideo", result.getPrimaryVideo());
            model.addAttribute("relatedVideos", result.getRelatedVideos());

        } catch (Exception e) {

            model.addAttribute("error", e.getMessage());
        }

        return "home";
    }
}