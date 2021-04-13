package com.kirshi.freya.server.bean;

import lombok.Builder;
import lombok.Data;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:Announcement.java
 * @LastModified:2021-04-14T02:09:19.815+08:00
 */

@Data
@Builder
public class Announcement {
    String title;
    String content;
    String coverImg;
    String isCancelOutside;
}
