package com.kirshi.freya.server.service;

import com.kirshi.freya.server.bean.Assist;
import com.kirshi.freya.server.bean.Device;
import com.kirshi.freya.server.dto.AssistCreaterDto;
import com.kirshi.freya.server.dto.AssistVisitorDto;

import java.util.List;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:AssistService.java
 * @LastModified:2021-04-06T00:53:35.756+08:00
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

    /**
     * 查询主控账户当前控制的设备列表
     *
     * @param uid peerUid主控账户ID
     * @return List<AssistVisitorDto>
     */
    List<AssistVisitorDto> queryAssistDto(String uid);

    /**
     * 查询被控账户当前被哪些用户控制着
     *
     * @param uid 作为被控者的账户ID
     * @return List<AssistCreaterDto>
     */
    List<AssistCreaterDto> queryAssistedDto(String uid);
}
