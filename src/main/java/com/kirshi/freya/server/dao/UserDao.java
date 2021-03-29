package com.kirshi.freya.server.dao;

import com.kirshi.freya.server.bean.User;
import com.kirshi.freya.server.db.DBManager;
import org.intellij.lang.annotations.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:UserDao.java
 * @LastModified:2021-03-29T17:17:13.239+08:00
 */

/**
 * @ClassName CustomerDao
 * @Description TODO
 * @Author Finger
 * @Date 1/4/2021
 **/
@Repository
public class UserDao {
    @Autowired
    private DBManager db;

    public List<User> selectUserByAccount(String account) {
        @Language("MySQL") String sql = "SELECT * FROM t_user WHERE account = ?";
        return db.queryForListObject(sql, new Object[]{account}, User.class);
    }

    public List<User> selectUserByOpenId(String openId) {
        @Language("MySQL") String sql = "SELECT * FROM t_user WHERE openid = ?";
        return db.queryForListObject(sql, new Object[]{openId}, User.class);
    }

    public List<User> selectUser(String account, String passwdEncoded) {
        @Language("MySQL") String sql = "SELECT * FROM t_user WHERE account = ? AND passwd = ?";
        Object[] args = new Object[]{account, passwdEncoded};
        return db.queryForListObject(sql, args, User.class);
    }

    public List<User> selectUser(String account) {
        @Language("MySQL") String sql = "SELECT * FROM t_user WHERE account = ?";
        Object[] args = new Object[]{account};
        return db.queryForListObject(sql, args, User.class);
    }

    public boolean insertUser(User user) {
        return db.insertObject(user) == 1;
    }
}
