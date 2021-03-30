package com.kirshi.freya.server.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:User.java
 * @LastModified:2021-03-30T16:57:51.395+08:00
 */

/**
 * @author Finger
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String account;
    private String passwd;
    private String nickname;
    private String openid;
    private Gender gender;
    private Timestamp regTime;

    public enum Gender {
        MAN(0), WOMAN(1), UNKNOWN(2);
        private int value = 0;

        Gender(int value) {     //必须是private的，否则编译错误
            this.value = value;
        }

        public static Gender valueOf(int value) {
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
