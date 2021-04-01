package com.kirshi.freya.server.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:Session.java
 * @LastModified:2021-04-01T20:03:11.006+08:00
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    private String uid;
    private String superkey;
}
