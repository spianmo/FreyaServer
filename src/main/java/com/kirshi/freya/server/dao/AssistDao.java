package com.kirshi.freya.server.dao;

import com.kirshi.freya.server.bean.Assist;
import com.kirshi.freya.server.bean.Device;
import com.kirshi.freya.server.dto.AssistCreaterDto;
import com.kirshi.freya.server.dto.AssistVisitorDto;
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
 * @LastModified:2021-04-06T00:53:35.734+08:00
 */
@Repository
public class AssistDao {
    @Resource
    private final JdbcTemplate jdbcTemplate;

    public AssistDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Device.Permission> queryVidPermissions(String vid) {
        @Language("MySQL") String sql = "SELECT permissions FROM t_assist WHERE vid = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Device.Permission.class), vid);
    }

    public boolean insert(Assist assist) {
        @Language("MySQL") String sql = "INSERT INTO t_assist (vid, uid, secret, access, permissions, device_id,status,create_time) VALUES (?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, assist.getVid(), assist.getUid(), assist.getSecret(), assist.getAccess(), assist.getPermissions(), assist.getDeviceId(), assist.getStatus(), assist.getCreateTime()) == 1;
    }

    public List<Assist> query(String vid) {
        @Language("MySQL") String sql = "SELECT * FROM t_assist WHERE vid = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Assist.class), vid);
    }

    public boolean update(String vid, Assist assist) {
        @Language("MySQL") String sql = "UPDATE t_assist SET secret = ?,access = ?,permissions = ? WHERE vid = ?";
        return jdbcTemplate.update(sql, assist.getSecret(), assist.getAccess(), assist.getPermissions(), vid) == 1;
    }

    public boolean updateStatus(String vid, Assist.Status status) {
        @Language("MySQL") String sql = "UPDATE t_assist SET status = ? WHERE vid = ?";
        return jdbcTemplate.update(sql, status, vid) == 1;
    }

    public boolean updatePeerUid(String vid, String peerUid) {
        @Language("MySQL") String sql = "UPDATE t_assist SET peer_uid = ? WHERE vid = ?";
        return jdbcTemplate.update(sql, peerUid, vid) == 1;
    }

    public boolean updateAlias(String vid, String alias) {
        @Language("MySQL") String sql = "UPDATE t_assist SET alias = ? WHERE vid = ?";
        return jdbcTemplate.update(sql, alias, vid) == 1;
    }

    public List<AssistVisitorDto> queryAssistDto(String uid) {
        @Language("MySQL") String sql = "SELECT peer_uid,vid,alias,model,electricity,t_assist.uid,account,t_user.nickname,secret,access,permissions,t_assist.device_id,t_device.status AS device_status,t_assist.status AS assist_status,create_time,last_ation_time FROM t_assist LEFT JOIN t_device ON t_assist.device_id = t_device.device_id LEFT JOIN t_user ON t_assist.uid = t_user.uid WHERE t_assist.peer_uid = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(AssistVisitorDto.class), uid);
    }

    public List<AssistCreaterDto> queryAssistedDto(String uid) {
        @Language("MySQL") String sql = "SELECT vid,t_assist.uid,secret,access,permissions,peer_uid,account AS peer_account,nickname AS peer_nickname,t_assist.status AS assist_status,create_time,last_ation_time FROM t_assist LEFT JOIN t_device ON t_assist.device_id = t_device.device_id LEFT JOIN t_user ON t_assist.peer_uid = t_user.uid WHERE t_assist.uid = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(AssistCreaterDto.class), uid);
    }
}
