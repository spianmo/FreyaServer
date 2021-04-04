package com.kirshi.freya.server.service;

import com.kirshi.freya.server.bean.Assist;
import com.kirshi.freya.server.bean.Device;

import java.util.List;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:AssistService.java
 * @LastModified:2021-04-05T01:21:15.105+08:00
 */

public interface AssistService {
    boolean insert(Assist assist);

    boolean isWork(String vid);

    List<Device.Permission> queryVidPermissions(String vid);

    Assist query(String vid);

    boolean settingAssist(String vid, Assist assist);

    boolean updateAssist(String vid, String alias);

    boolean updateStatus(String vid, Assist.Status status);

    boolean updatePeerUid(String vid, String peerUid);
}
