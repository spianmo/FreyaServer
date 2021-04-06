package com.kirshi.freya.server.dto;

import com.kirshi.freya.server.bean.Assist;
import com.kirshi.freya.server.bean.Device;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:AssistCreaterDto.java
 * @LastModified:2021-04-06T18:07:44.494+08:00
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssistCreaterDto {
    String vid;
    String uid;
    String secret;
    Assist.Access access;
    Device.Permission[] permissions;
    String model;
    String deviceId;
    String peerUid;
    String peerAccount;
    String peerNickname;
    Assist.Status assistStatus;
    Timestamp createTime;
    Timestamp lastActionTime;
}
