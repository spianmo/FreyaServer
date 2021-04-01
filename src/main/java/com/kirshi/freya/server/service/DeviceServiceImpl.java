package com.kirshi.freya.server.service;

import com.kirshi.freya.server.bean.Device;
import com.kirshi.freya.server.dao.DeviceDao;
import com.kirshi.freya.server.dao.InviteDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:DeviceServiceImpl.java
 * @LastModified:2021-04-01T20:03:11.074+08:00
 */
@Service
public class DeviceServiceImpl implements DeviceService {
    @Resource
    DeviceDao mDeviceDao;
    @Resource
    InviteDao mInviteDao;

    @Override
    public boolean insertDevice(Device device) {
        return mDeviceDao.insert(device);
    }

    @Override
    public boolean deleteDevice(Device device) {
        return mDeviceDao.delete(device.getUid(), device.getDeviceId());
    }

    @Override
    public boolean updateDevice(Device device) {
        return mDeviceDao.update(device.getUid(), device.getDeviceId(), device);
    }

    @Override
    public boolean queryExist(String uid, String deviceId) {
        return mDeviceDao.query(uid, deviceId).size() == 1;
    }

    @Override
    public List<Device> queryByUid(String uid) {
        return mDeviceDao.queryByUid(uid);
    }

    @Override
    public List<Device> queryByDeviceId(String deviceId) {
        return mDeviceDao.queryByDeviceId(deviceId);
    }

    @Override
    public List<Device.Permission> queryVidPermissions(String vid) {
        return mInviteDao.queryVidPermissions(vid);
    }

}
