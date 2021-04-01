package com.kirshi.freya.server.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:User.java
 * @LastModified:2021-04-01T20:03:11.009+08:00
 */

/**
 * @author Finger
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String uid;
    @NotNull(message = "账号不能为空")
    private String account;
    @NotNull(message = "密码不能为空")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwd;
    private String nickname;
    private String openid;
    private Gender gender;
    private Timestamp regTime;

    public enum Gender {
        MAN("MAN"), WOMAN("WOMAN"), UNKNOWN("UNKOWN");
        private final String value;

        Gender(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
