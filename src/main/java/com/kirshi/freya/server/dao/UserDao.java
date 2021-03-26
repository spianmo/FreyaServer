package com.kirshi.freya.server.dao;

import com.kirshi.freya.server.bean.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

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
        String sql = "SELECT * FROM t_user WHERE account = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class),account);
    }

    public List<User> selectUserByOpenId(String openId) {
        String sql = "SELECT * FROM t_user WHERE openid = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class),openId);
    }

    public List<User> selectUser(String account,String passwdEncoded){
        String sql  = "SELECT * FROM t_user WHERE account = ? AND passwd = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class),account,passwdEncoded);
    }
}
