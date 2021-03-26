package com.kirshi.freya.server.bean;

import lombok.Data;

import java.sql.Date;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:User.java
 * @LastModified:2021-03-27T01:09:45.744+08:00
 */

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
