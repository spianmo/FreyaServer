package com.kirshi.freya.server.bean;

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
 * @FileName:Device.java
 * @LastModified:2021-04-01T20:03:10.992+08:00
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    int id;
    @NotNull(message = "设备ID不能为空")
    String deviceId;
    String remark;
    String model;
    @NotNull(message = "UID不能为空")
    String uid;
    String electricity;
    Status status;
    Timestamp lastActionTime;
    String vid;

    public enum Status {
        Online("Online"), Offline("Offline"), Unknown("Unknown"), Forbid("Forbid"), NotConnected("NotConnected");
        private final String value;

        Status(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

    public enum Permission {
        ScreenControl("ScreenControl"), Camera("Camera"), FileManage("FileManage"), GPS("GPS");
        private final String value;

        Permission(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
