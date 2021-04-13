package com.kirshi.freya.server.bean;

import lombok.Builder;
import lombok.Data;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:Version.java
 * @LastModified:2021-04-14T02:09:19.821+08:00
 */

@Data
@Builder
public class Version {
    int versionNum;
    String versionName;
    String downloadUrl;
    String description;
    String updateCoverImg;
    String hash;
    String apkSize;
}
