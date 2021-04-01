package com.kirshi.freya.server.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:Invite.java
 * @LastModified:2021-04-01T20:03:11.000+08:00
 */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invite {
    String vid;
    String uid;
    String secret;
    Access access;
    Device.Permission[] permissions;
    Boolean used;

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
}
