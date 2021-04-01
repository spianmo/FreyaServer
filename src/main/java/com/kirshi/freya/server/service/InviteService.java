package com.kirshi.freya.server.service;

import com.kirshi.freya.server.bean.Invite;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:InviteService.java
 * @LastModified:2021-04-01T20:03:11.078+08:00
 */

public interface InviteService {
    boolean insert(Invite invite);

    Invite query(Invite invite);
}
