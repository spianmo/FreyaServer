package com.kirshi.freya.server.dao;

import com.kirshi.freya.server.bean.Session;
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
 * @FileName:SessionDao.java
 * @LastModified:2021-04-02T01:00:53.292+08:00
 */
@Repository
public class SessionDao {
    @Resource
    private final JdbcTemplate jdbcTemplate;

    public SessionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public boolean insert(String uid, String superkey) {
        @Language("MySQL") String sql = "insert into t_session (uid, superkey) VALUES (?,?)";
        return jdbcTemplate.update(sql, uid, superkey) == 1;
    }

    public Session query(String uid) {
        @Language("MySQL") String sql = "select * from t_session where uid = ?";
        List<Session> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Session.class), uid);
        return list.isEmpty() ? null : list.get(0);
    }

    public boolean update(String uid, String superkey) {
        @Language("MySQL") String sql = "update t_session set superkey = ? where uid = ?";
        return jdbcTemplate.update(sql, superkey, uid) == 1;
    }
}
