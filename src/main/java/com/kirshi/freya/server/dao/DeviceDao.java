package com.kirshi.freya.server.dao;

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
 * @FileName:DeviceDao.java
 * @LastModified:2021-04-01T20:03:11.042+08:00
 */
@Repository
public class DeviceDao {

    @Resource
    private final JdbcTemplate jdbcTemplate;

    public DeviceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean insert(Device device) {
        @Language("MySQL") String sql = "insert into t_device (device_id, remark, model, uid, electricity, status, last_ation_time, vid) VALUES (?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, device.getDeviceId(), device.getRemark(), device.getModel(), device.getModel(), device.getUid(), device.getElectricity(), device.getStatus(), device.getLastActionTime(), device.getVid()) == 1;
    }

    public List<Device> query(String uid, String deviceId) {
        @Language("MySQL") String sql = "select * from t_device where uid = ? and device_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Device.class), uid, deviceId);
    }

    public List<Device> queryByUid(String uid) {
        @Language("MySQL") String sql = "select * from t_device where uid = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Device.class), uid);
    }

    public boolean delete(String uid, String deviceId) {
        @Language("MySQL") String sql = "delete from t_device where uid = ? and device_id = ?";
        return jdbcTemplate.update(sql, new BeanPropertyRowMapper<>(Device.class), uid, deviceId) == 1;
    }

    public boolean update(String uid, String deviceId, Device device) {
        @Language("MySQL") String sql = "update t_device set remark = ?, model = ?,electricity = ?,status = ?,last_ation_time = ? where uid = ? and device_id = ?";
        return jdbcTemplate.update(sql, device.getRemark(), device.getModel(), device.getElectricity(), device.getStatus(), device.getLastActionTime(), uid, deviceId) == 1;
    }

    public List<Device> queryByDeviceId(String deviceId) {
        @Language("MySQL") String sql = "select * from t_device where device_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Device.class), deviceId);
    }
}
