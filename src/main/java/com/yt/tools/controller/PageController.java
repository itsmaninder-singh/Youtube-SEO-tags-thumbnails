package com.yt.tools.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping({"/","home"})
    public String home(){
        return "home";//we use this for thymeleaf static page
    }
    @GetMapping("/video-details")
    public String VideoDetails(){
        return "videoDetails";//we use this for thymeleaf static page
    }
}
