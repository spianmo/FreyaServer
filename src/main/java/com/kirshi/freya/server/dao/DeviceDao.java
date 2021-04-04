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
 * @LastModified:2021-04-05T01:21:15.091+08:00
 */
@Repository
public class DeviceDao {

    @Resource
    private final JdbcTemplate jdbcTemplate;

    public DeviceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean insert(Device device) {
        @Language("MySQL") String sql = "insert into t_device (device_id, model, electricity, status, last_ation_time) VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(sql, device.getDeviceId(), device.getModel(), device.getElectricity(), device.getStatus(), device.getLastActionTime()) == 1;
    }

    public List<Device> query(String deviceId) {
        @Language("MySQL") String sql = "select * from t_device where device_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Device.class), deviceId);
    }

    public boolean update(String deviceId, Device device) {
        @Language("MySQL") String sql = "update t_device set model = ?,electricity = ?,status = ?,last_ation_time = ? where device_id = ?";
        return jdbcTemplate.update(sql, device.getModel(), device.getElectricity(), device.getStatus(), device.getLastActionTime(), deviceId) == 1;
    }

}
