package com.kirshi.freya.server.controller;

import com.kirshi.freya.server.annotation.RequireLogin;
import com.kirshi.freya.server.base.BaseResponse;
import com.kirshi.freya.server.base.CodeConstant;
import com.kirshi.freya.server.bean.Device;
import com.kirshi.freya.server.service.DeviceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:DeviceController.java
 * @LastModified:2021-04-05T01:21:15.080+08:00
 */

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Resource
    private DeviceService mDeviceService;

    @RequireLogin
    @PostMapping(value = "/update")
    public BaseResponse<String> update(@Valid @RequestBody Device device) {
        if (mDeviceService.queryExist(device.getDeviceId())) {
            boolean result = mDeviceService.updateDevice(device);
            return result ? new BaseResponse<>(CodeConstant.Success, "设备修改成功") : new BaseResponse<>(CodeConstant.Faild, "设备修改失败");
        } else {
            boolean result = mDeviceService.insertDevice(device);
            return result ? new BaseResponse<>(CodeConstant.Success, "设备上传成功") : new BaseResponse<>(CodeConstant.Faild, "设备上传失败");
        }
    }

}
