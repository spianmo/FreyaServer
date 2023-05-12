CREATE TABLE t_device
(
    device_id        varchar(32)                                                     NOT NULL COMMENT '设备唯一标识'
        PRIMARY KEY,
    model            varchar(30)                                                     NULL COMMENT '设备型号及系统版本',
    electricity      varchar(10)                                                     NULL COMMENT '电量剩余',
    status           enum ('Online', 'Offline', 'Unknown', 'Forbid', 'NotConnected') NOT NULL COMMENT '当前设备状态',
    last_action_time timestamp DEFAULT CURRENT_TIMESTAMP                             NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次活动时间'
)
    COMMENT '被控设备表';

INSERT INTO t_device (device_id, model, electricity, status, last_action_time) VALUES ('2B2C3BA272DF54DCD0E2B0BF0E5A5B54', 'HUAWEI/YAL-AL10 Android10', '64|3', 'Online', '2021-04-20 13:36:13');
INSERT INTO t_device (device_id, model, electricity, status, last_action_time) VALUES ('818C190ABF1101D190C2962645CDEC2F', 'Xiaomi/MI 9 Android11', '39|3', 'Online', '2021-04-16 09:41:13');
INSERT INTO t_device (device_id, model, electricity, status, last_action_time) VALUES ('A1E5987396F4D7C4A23222B95D99E28E', 'Xiaomi/Mi 10 Android11', '26|2', 'Online', '2021-04-19 23:40:06');
INSERT INTO t_device (device_id, model, electricity, status, last_action_time) VALUES ('A25AE004EADF95406855FFBAD5653993', 'Honor 20 Android10', '9|3', 'Offline', '2021-04-10 23:41:52');