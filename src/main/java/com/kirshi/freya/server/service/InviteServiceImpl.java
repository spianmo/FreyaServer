package com.kirshi.freya.server.service;

import com.kirshi.freya.server.bean.Invite;
import com.kirshi.freya.server.dao.InviteDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:InviteServiceImpl.java
 * @LastModified:2021-04-01T20:03:11.083+08:00
 */
@Service
public class InviteServiceImpl implements InviteService {
    @Resource
    InviteDao mInviteDao;

    @Override
    public boolean insert(Invite invite) {
        return mInviteDao.insert(invite);
    }

    @Override
    public Invite query(Invite invite) {
        return mInviteDao.query(invite.getVid());
    }
}
