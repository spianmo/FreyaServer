package com.kirshi.freya.server.controller;

import com.kirshi.freya.server.bean.Announcement;
import com.kirshi.freya.server.bean.Version;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:AppController.java
 * @LastModified:2021-11-08T10:34:54.910+08:00
 */

@RestController
@RequestMapping("/app")
public class AppController {

    @GetMapping("/sig")
    public String sig() {
        return "123456md5";
    }

    @GetMapping("/version")
    public Version update() {
        return Version.builder().build();
    }


    @GetMapping("/splash")
    public String getSplashCover() {
        return "https://shadowq.com/api/bing/bing.php";
    }

    @GetMapping("/announce")
    public Announcement getAnnouncement() {
        return Announcement.builder().build();
    }

    @GetMapping("/download")
    public ModelAndView download() {
        return new ModelAndView("download");
    }
}
