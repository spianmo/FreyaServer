package com.kirshi.freya.server.service;

import com.kirshi.freya.server.bean.User;
import com.kirshi.freya.server.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:AccountServiceImpl.java
 * @LastModified:2021-03-30T16:57:51.466+08:00
 */

/**
 * @author Finger
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    UserDao mUserDao;

    @Override
    public List<User> queryExistUser(String version, User user) {
        List<User> users = new ArrayList<>();
        if (version.equals("social")) {
            users = mUserDao.selectUserByOpenId(user.getOpenid());
        } else if (version.equals("common")) {
            users = mUserDao.selectUserByAccount(user.getAccount());
        }
        return users;
    }

    @Override
    public boolean checkPasswd(User user) {
        return mUserDao.selectUser(user.getAccount(), user.getPasswd()).size() == 1;
    }

    @Override
    public boolean insertUser(User user) {
        return mUserDao.insertUser(user);
    }

    @Override
    public boolean isUserExist(String condition, String str) {
        return mUserDao.selectUserByAccount(str).isEmpty();
    }
}
