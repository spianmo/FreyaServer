package com.kirshi.freya.server.controller;

import com.kirshi.freya.server.annotation.RequireLogin;
import com.kirshi.freya.server.base.BaseResponse;
import com.kirshi.freya.server.base.CodeConstant;
import com.kirshi.freya.server.bean.Device;
import com.kirshi.freya.server.service.DeviceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:DeviceController.java
 * @LastModified:2021-04-02T09:20:11.646+08:00
 */

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Resource
    private DeviceService mDeviceService;

    @RequireLogin
    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    public BaseResponse<String> add(@Valid @RequestBody Device device) {
        if (mDeviceService.queryExist(device.getDeviceId(), device.getUid())) {
            return new BaseResponse<>(CodeConstant.Exists, "设备已存在");
        }
        device.setStatus(Device.Status.NotConnected);
        if (mDeviceService.insertDevice(device)) {
            return new BaseResponse<>(CodeConstant.Success, "新增设备成功");
        }
        return new BaseResponse<>(CodeConstant.Faild, "新增设备失败");
    }

    @RequireLogin
    @PostMapping(value = "/delete")
    public BaseResponse<String> delete(@Valid @RequestBody Device device) {
        if (mDeviceService.queryExist(device.getDeviceId(), device.getUid())) {
            boolean result = mDeviceService.deleteDevice(device);
            return result ? new BaseResponse<>(CodeConstant.Success, "设备删除成功") : new BaseResponse<>(CodeConstant.Faild, "设备删除失败");
        }
        return new BaseResponse<>(CodeConstant.NotExists, "设备不存在");
    }

    @RequireLogin
    @PostMapping(value = "/update")
    public BaseResponse<String> update(@Valid @RequestBody Device device) {
        if (mDeviceService.queryExist(device.getDeviceId(), device.getUid())) {
            boolean result = mDeviceService.updateDevice(device);
            return result ? new BaseResponse<>(CodeConstant.Success, "设备修改成功") : new BaseResponse<>(CodeConstant.Faild, "设备修改失败");
        }
        return new BaseResponse<>(CodeConstant.NotExists, "设备不存在");
    }

    @RequireLogin
    @PostMapping(value = "/queryPermissions")
    public BaseResponse<List<Device.Permission>> queryPermissions(@RequestParam(name = "vid") String vid) {
        List<Device.Permission> permissions = mDeviceService.queryVidPermissions(vid);
        return permissions.isEmpty() ? new BaseResponse<>(CodeConstant.Faild, "获取设备权限失败") : new BaseResponse<>(CodeConstant.Success, permissions);
    }
}
