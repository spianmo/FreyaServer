package com.kirshi.freya.server.service;

import com.kirshi.freya.server.bean.User;

import java.util.List;

public interface AccountService {

    List<User> queryExistUser(String version, User user);

    boolean checkPasswd(User user);
}
