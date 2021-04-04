package com.kirshi.freya.server.dao;

import com.kirshi.freya.server.bean.Assist;
import com.kirshi.freya.server.bean.Device;
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
 * @FileName:AssistDao.java
 * @LastModified:2021-04-05T01:21:15.087+08:00
 */
@Repository
public class AssistDao {
    @Resource
    private final JdbcTemplate jdbcTemplate;

    public AssistDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Device.Permission> queryVidPermissions(String vid) {
        @Language("MySQL") String sql = "select permissions from t_assist where vid = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Device.Permission.class), vid);
    }

    public boolean insert(Assist assist) {
        @Language("MySQL") String sql = "insert into t_assist (vid, uid, secret, access, permissions, device_id,status) VALUES (?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, assist.getVid(), assist.getUid(), assist.getSecret(), assist.getAccess(), assist.getPermissions(), assist.getDeviceId(), assist.getStatus()) == 1;
    }

    public List<Assist> query(String vid) {
        @Language("MySQL") String sql = "select * from t_assist where vid = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Assist.class), vid);
    }

    public boolean update(String vid, Assist assist) {
        @Language("MySQL") String sql = "update t_assist set secret = ?,access = ?,permissions = ? where vid = ?";
        return jdbcTemplate.update(sql, assist.getSecret(), assist.getAccess(), assist.getPermissions(), vid) == 1;
    }

    public boolean updateStatus(String vid, Assist.Status status) {
        @Language("MySQL") String sql = "update t_assist set status = ? where vid = ?";
        return jdbcTemplate.update(sql, status, vid) == 1;
    }

    public boolean updatePeerUid(String vid, String peerUid) {
        @Language("MySQL") String sql = "update t_assist set peer_uid = ? where vid = ?";
        return jdbcTemplate.update(sql, peerUid, vid) == 1;
    }

    public boolean updateAlias(String vid, String alias) {
        @Language("MySQL") String sql = "update t_assist set alias = ? where vid = ?";
        return jdbcTemplate.update(sql, alias, vid) == 1;
    }
}
