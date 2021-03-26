package com.kirshi.freya.server.service;

import com.kirshi.freya.server.bean.User;
import com.kirshi.freya.server.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


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
}
