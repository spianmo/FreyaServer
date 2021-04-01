package com.kirshi.freya.server.service;

import com.kirshi.freya.server.bean.Session;
import com.kirshi.freya.server.dao.SessionDao;
import com.kirshi.freya.server.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:SessionServiceImpl.java
 * @LastModified:2021-04-02T01:00:53.299+08:00
 */

@Slf4j
@Service
public class SessionServiceImpl implements SessionService {
    @Resource
    SessionDao mSessionDao;

    @Override
    public String generateSuperkey(String uid) {
        String superkey = RandomUtil.createRandomCharData(24).toUpperCase();
        boolean result = mSessionDao.insert(uid, superkey);
        log.debug(result ? "generateSuperkey Success" : "generateSuperkey Faild");
        return superkey;
    }

    @Override
    public Session getSuperkey(String uid) {
        return mSessionDao.query(uid);
    }

    @Override
    public String updateSuperkey(String uid) {
        String superkey = RandomUtil.createRandomCharData(24).toUpperCase();
        boolean result = mSessionDao.update(uid, superkey);
        log.debug(result ? "updateSuperkey Success" : "updateSuperkey Faild");
        return superkey;
    }
}
