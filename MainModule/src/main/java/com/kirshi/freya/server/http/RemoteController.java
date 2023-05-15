package com.kirshi.freya.server.http;

import com.kirshi.freya.server.base.BaseResponse;
import com.kirshi.freya.server.service.RemoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Copyright (c) 2023
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:RemoteController.java
 * @LastModified:2023-05-16T02:54:07.583+08:00
 */

@RestController
@RequestMapping("/remote")
public class RemoteController {

    @Resource
    RemoteService remoteService;

    @GetMapping("/create")
    public BaseResponse<?> createValidKey() {
        return BaseResponse.success(remoteService.createValidKey(4));
    }
}
