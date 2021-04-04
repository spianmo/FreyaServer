package com.kirshi.freya.server.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:Assist.java
 * @LastModified:2021-04-05T01:21:15.065+08:00
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
