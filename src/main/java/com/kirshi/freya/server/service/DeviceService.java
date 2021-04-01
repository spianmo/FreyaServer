package com.kirshi.freya.server.service;

import com.kirshi.freya.server.bean.Device;

import java.util.List;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:DeviceService.java
 * @LastModified:2021-04-01T20:03:11.071+08:00
 */

public interface DeviceService {
    boolean insertDevice(Device device);

    boolean deleteDevice(Device device);

    boolean updateDevice(Device device);

    /**
     * *
     *
     * @param uid      主控账号UID
     * @param deviceId 受控端设备ID
     * @return 是否存在
     */
    boolean queryExist(String uid, String deviceId);

    /**
     * 查询账号当前绑定了多少设备
     *
     * @param uid 主控账号UID
     * @return 当前UID绑定的设备实体集
     */
    List<Device> queryByUid(String uid);

    /**
     * 查询当前设备被哪些账号绑定
     *
     * @param deviceId 设备ID
     * @return
     */
    List<Device> queryByDeviceId(String deviceId);

    List<Device.Permission> queryVidPermissions(String vid);

}
