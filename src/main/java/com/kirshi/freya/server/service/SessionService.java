package com.kirshi.freya.server.service;

import com.kirshi.freya.server.bean.Session;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:SessionService.java
 * @LastModified:2021-04-01T20:03:11.087+08:00
 */

public interface SessionService {
    boolean generateSession(String uid);

    Session getSuperkey(String uid);

    boolean updatesuperkey(String uid);
}
