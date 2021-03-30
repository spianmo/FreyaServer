package com.kirshi.freya.server.dao;

import com.kirshi.freya.server.bean.User;
import com.kirshi.freya.server.socket.utils.Log;
import org.intellij.lang.annotations.Language;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:UserDao.java
 * @LastModified:2021-03-30T16:57:51.460+08:00
 */

/**
 * @ClassName CustomerDao
 * @Description TODO
 * @Author Finger
 * @Date 1/4/2021
 **/
@Repository
public class UserDao {
    @Resource
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> selectUserByAccount(String account) {
        @Language("MySQL") String sql = "SELECT * FROM t_user WHERE account = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), account);
    }

    public List<User> selectUserByOpenId(String openId) {
        @Language("MySQL") String sql = "SELECT * FROM t_user WHERE openid = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), openId);
    }

    public List<User> selectUser(String account, String passwdEncoded) {
        Log.e(account + " " + passwdEncoded);
        @Language("MySQL") String sql = "SELECT * FROM t_user WHERE account = ? AND passwd = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), account, passwdEncoded);
    }

    public boolean insertUser(User user) {
        @Language("MySQL") String sql = "insert into t_user (account,passwd,nickname,openid,gender,reg_time) values (?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, user.getAccount(), user.getPasswd(), user.getNickname(), user.getOpenid(), user.getGender().name(), user.getRegTime()) == 1;
    }
}
