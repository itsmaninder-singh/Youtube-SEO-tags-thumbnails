package com.yt.tools.controller;

import com.yt.tools.Service.ThumbnailService;
import com.yt.tools.Service.Ytservice;
import com.yt.tools.models.SearchVideo;
import com.yt.tools.models.VideoDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class YtVideoController {

    private final ThumbnailService thumbnailService;
    private final Ytservice ytservice;

    @GetMapping("/youtube/video-details")
    public String showVideoForm() {
        return "video-details";   // Thymeleaf template
    }

    @PostMapping("/youtube/video-details")
    public String fetchVideoDetails(@RequestParam String videoUrlOrId,
                                    Model model) {

        try {

            String videoId = thumbnailService.extractVideoID(videoUrlOrId);

            VideoDetails details = ytservice.getVideoDetails(videoId);

            if(details == null){
                model.addAttribute("error","video not foumd");
            }
            else{
                model.addAttribute("videoDetails",details);
            }
        } catch (Exception e) {

            model.addAttribute("error", e.getMessage());

        }

        return "video-details";
    }
}