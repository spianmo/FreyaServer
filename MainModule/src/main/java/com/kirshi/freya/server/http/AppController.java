package com.kirshi.freya.server.http;

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
}
