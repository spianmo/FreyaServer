package com.kirshi.freya.server.dao;

import com.kirshi.freya.server.bean.Device;
import com.kirshi.freya.server.bean.Invite;
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
 * @FileName:InviteDao.java
 * @LastModified:2021-04-01T20:03:11.046+08:00
 */
@Repository
public class InviteDao {
    @Resource
    private final JdbcTemplate jdbcTemplate;

    public InviteDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Device.Permission> queryVidPermissions(String vid) {
        @Language("MySQL") String sql = "select permissions from t_invite where vid = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Device.Permission.class), vid);
    }

    public boolean insert(Invite invite) {
        @Language("MySQL") String sql = "insert into t_invite (vid, uid, secret, access, permissions, used) VALUES (?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, invite.getVid(), invite.getUid(), invite.getSecret(), invite.getAccess(), invite.getPermissions(), invite.getUsed()) == 1;
    }

    public Invite query(String vid) {
        @Language("MySQL") String sql = "select * from t_invite where vid = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Invite.class), vid).get(0);
    }
}
