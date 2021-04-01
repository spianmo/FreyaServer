package com.kirshi.freya.server.service;

import com.kirshi.freya.server.bean.Session;
import com.kirshi.freya.server.dao.SessionDao;
import com.kirshi.freya.server.utils.RandomUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:SessionServiceImpl.java
 * @LastModified:2021-04-01T20:03:11.091+08:00
 */

@Service
public class SessionServiceImpl implements SessionService {
    @Resource
    SessionDao mSessionDao;

    @Override
    public boolean generateSession(String uid) {
        String superkey = RandomUtil.createRandomCharData(24).toUpperCase();
        return mSessionDao.insert(uid, superkey);
    }

    @Override
    public Session getSuperkey(String uid) {
        return mSessionDao.query(uid);
    }

    @Override
    public boolean updatesuperkey(String uid) {
        String superkey = RandomUtil.createRandomCharData(24).toUpperCase();
        return mSessionDao.update(uid, superkey);
    }
}
