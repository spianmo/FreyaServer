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
 * @FileName:Assist.java
 * @LastModified:2021-04-06T00:53:35.721+08:00
 */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Assist {
    String vid;
    String alias;
    String uid;
    String secret;
    Access access;
    Device.Permission[] permissions;
    String deviceId;
    String peerUid;
    Status status;
    Timestamp createTime;

    public enum Access {
        Guest("Guest"), Official("Official");

        private final String value;

        Access(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

    public enum Status {
        Unused("Unused"), Used("Used"), Fired("Fired");
        private final String value;

        Status(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
