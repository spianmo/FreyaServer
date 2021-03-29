package com.kirshi.freya.server.bean;

import com.kirshi.freya.server.db.annotation.Table;
import lombok.Data;

import java.sql.Date;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:User.java
 * @LastModified:2021-03-29T17:17:13.231+08:00
 */

/**
 * @author Finger
 */
@Data
@Table("t_user")
public class User {
    private int id;
    private String account;
    private String passwd;
    private String nickname;
    private String openid;
    private Gender gender;
    private Date regTime;

    public enum Gender {
        MAN(0), WOMAN(1), UNKNOWN(2);
        private int value = 0;

        Gender(int value) {     //必须是private的，否则编译错误
            this.value = value;
        }

        public static Gender valueOf(int value) {    //手写的从int到enum的转换函数
            switch (value) {
                case 0:
                    return MAN;
                case 1:
                    return WOMAN;
                case 2:
                    return UNKNOWN;
                default:
                    return null;
            }
        }

        public int value() {
            return this.value;
        }
    }
}
