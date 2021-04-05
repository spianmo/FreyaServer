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
 * @FileName:AssistVisitorDto.java
 * @LastModified:2021-04-06T00:53:35.753+08:00
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssistVisitorDto {
    String peerUid;
    String vid;
    String alias;
    String model;
    String electricity;
    String uid;
    String account;
    String nickname;
    String secret;
    Assist.Access access;
    Device.Permission[] permissions;
    String deviceId;
    Device.Status deviceStatus;
    Assist.Status assistStatus;
    Timestamp createTime;
    Timestamp lastActionTime;
}
