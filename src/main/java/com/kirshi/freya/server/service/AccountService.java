package com.kirshi.freya.server.service;

import com.kirshi.freya.server.bean.User;

import java.util.List;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:AccountService.java
 * @LastModified:2021-03-29T17:17:13.245+08:00
 */

public interface AccountService {

    List<User> queryExistUser(String version, User user);

    boolean checkPasswd(User user);

    boolean insertUser(User user);

    boolean isUserExist(String condition, String str);
}
