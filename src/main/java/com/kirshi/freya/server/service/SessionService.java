package com.kirshi.freya.server.service;

import com.kirshi.freya.server.bean.Session;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:SessionService.java
 * @LastModified:2021-04-02T01:00:53.297+08:00
 */

public interface SessionService {
    String generateSuperkey(String uid);

    Session getSuperkey(String uid);

    String updateSuperkey(String uid);
}
