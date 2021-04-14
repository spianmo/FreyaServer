/*
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:FreyaServer.sql
 * @LastModified:2021-04-14T19:59:35.453+08:00
 */

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

CREATE TABLE t_session
(
    uid      varchar(10) NOT NULL COMMENT '账号ID'
        PRIMARY KEY,
    superkey varchar(24) NOT NULL COMMENT '访问restful接口的账号凭证'
)
    COMMENT '账户登录凭证表';

CREATE TABLE t_user
(
    uid      varchar(32)                         NOT NULL COMMENT '用户ID'
        PRIMARY KEY,
    account  varchar(20)                         NOT NULL COMMENT '账号',
    passwd   varchar(50)                         NOT NULL COMMENT '加密存储的密码',
    nickname varchar(20)                         NOT NULL COMMENT '昵称',
    openid   varchar(50)                         NOT NULL COMMENT '社会化登录获取的唯一标识',
    gender   enum ('MAN', 'WOMAN', 'UNKNOWN')    NOT NULL COMMENT '性别',
    reg_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
    CONSTRAINT account
        UNIQUE (account)
)
    COMMENT '用户表';

CREATE TABLE t_assist
(
    vid         varchar(10)                         NOT NULL COMMENT '协助会话ID'
        PRIMARY KEY,
    alias       varchar(32)                         NULL COMMENT '协助会话中被控的备注别名，由主控设置',
    uid         varchar(10)                         NOT NULL COMMENT '被控账户ID',
    secret      varchar(6)                          NOT NULL COMMENT '协助会话的密码',
    access      enum ('Guest', 'Official')          NOT NULL COMMENT '协助会话的类型',
    permissions json                                NOT NULL COMMENT '协助会话的权限',
    device_id   varchar(32)                         NOT NULL COMMENT '被控设备ID',
    peer_uid    varchar(10)                         NULL COMMENT '主控的用户ID',
    status      enum ('Unused', 'Used', 'Fired')    NOT NULL COMMENT '会话状态',
    create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '会话创建时间',
    CONSTRAINT t_invite_t_device_device_id_fk
        FOREIGN KEY (device_id) REFERENCES t_device (device_id),
    CONSTRAINT t_invite_t_user_uid_fk
        FOREIGN KEY (uid) REFERENCES t_user (uid),
    CONSTRAINT t_invite_t_user_uid_fk_2
        FOREIGN KEY (peer_uid) REFERENCES t_user (uid)
)
    COMMENT '协助会话表';

