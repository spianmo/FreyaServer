package com.kirshi.freya.server.service;

import com.kirshi.freya.server.bean.Device;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:DeviceService.java
 * @LastModified:2021-04-05T01:21:15.114+08:00
 */

public interface DeviceService {
    boolean insertDevice(Device device);

    boolean updateDevice(Device device);

    boolean queryExist(String deviceId);

}
