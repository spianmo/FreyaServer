package com.kirshi.freya.server.http;

import com.kirshi.freya.server.base.BaseResponse;
import com.kirshi.freya.server.base.HttpStatus;
import com.kirshi.freya.server.service.RemoteService;
import com.kirshi.freya.server.utils.RandomUtil;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
