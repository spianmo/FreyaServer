package com.kirshi.freya.server.service;

import com.kirshi.freya.server.bean.Device;
import com.kirshi.freya.server.dao.DeviceDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:DeviceServiceImpl.java
 * @LastModified:2021-04-05T01:21:15.117+08:00
 */
@Service
public class DeviceServiceImpl implements DeviceService {
    @Resource
    DeviceDao mDeviceDao;

    @Override
    public boolean insertDevice(Device device) {
        return mDeviceDao.insert(device);
    }

    @Override
    public boolean updateDevice(Device device) {
        return mDeviceDao.update(device.getDeviceId(), device);
    }

    @Override
    public boolean queryExist(String deviceId) {
        return mDeviceDao.query(deviceId).size() == 1;
    }

}
