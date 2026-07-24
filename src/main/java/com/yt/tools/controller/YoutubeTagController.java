package com.yt.tools.controller;

import com.yt.tools.Service.Ytservice;
import com.yt.tools.models.searchVideo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/youtube")
public class YoutubeTagController {
    @Value("${youtube.api.key")
    private String ApiKey;
    private Ytservice ytservice;
    private boolean isApiKeyConfigure(){
        return ApiKey!=null && !ApiKey.isEmpty();
    }

    @PostMapping("/search")
    public String VideoTags(@RequestParam ("videoTitle") String videoTitle, Model model){
        if(!isApiKeyConfigure()){
            model.addAttribute("error","Api key is not configured");
            return "home";
        }


        if(videoTitle == null || videoTitle.isEmpty()){
            model.addAttribute("error","yt title is required u piece of shit !!");
            return "home";
        }

        try {
            searchVideo result = ytservice.searchVideos(videoTitle);
            model.addAttribute("primaryvideo",result.getPrimaryvideo());
            model.addAttribute("realtedVideos",result.getRelatedvideos());
            return "home";


        }catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "home";

        }
        return null;
    }

}
