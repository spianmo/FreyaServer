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
 * @LastModified:2021-04-05T01:21:15.068+08:00
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    @NotNull(message = "设备ID不能为空")
    String deviceId;
    String model;
    String electricity;
    Status status;
    Timestamp lastActionTime;

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
