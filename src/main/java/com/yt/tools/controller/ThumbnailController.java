package com.yt.tools.controller;

import com.yt.tools.Service.ThumbnailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThumbnailController {

    @Autowired
    ThumbnailService services;

    @GetMapping("/thumbnails")
    public String getThumbnail(){
        return "thumbnail";//we use this for thymeleaf static page
    }
    @PostMapping("/get-thumbnail")
    public String showThumbnail(@RequestParam ("videoUrlorId") String videoUrldorId, Model model){
        String videoId= services.extractVideoID(videoUrldorId);
        if(videoId==null){
            model.addAttribute("error ","Invalid url link" );
            return "thumbnails";
        }
        String thumbnailUrl= "https://img.youyube.com/vi/" + videoId +"/maxresdefault.jpg";
        model.addAttribute("thumbnailUrl", thumbnailUrl);

        return "thumbnails";

    }
}
