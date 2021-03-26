package com.kirshi.freya.server.bean;

import lombok.Data;

import java.sql.Date;

/**
 * @author Finger
 */
@Data
public class User {
    private int id;
    private String account;
    private String passwd;
    private String nickname;
    private String openid;
    private short gender;
    private Date regTime;
}
