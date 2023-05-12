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

INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('0204854267', null, '9491321164', '123', 'Guest', '["ScreenControl", "ScreenView", "Camera", "FileManage", "GPS"]', '2B2C3BA272DF54DCD0E2B0BF0E5A5B54', '9849194630', 'Fired', '2021-04-14 19:31:26');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('0460386609', null, '9849194630', '123', 'Guest', '["ScreenControl", "ScreenView", "Camera", "FileManage", "GPS"]', '818C190ABF1101D190C2962645CDEC2F', '9491321164', 'Used', '2021-04-14 20:04:10');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('0544956922', null, '8104076675', '709548', 'Official', '["ScreenControl", "ScreenView"]', '818C190ABF1101D190C2962645CDEC2F', '3465562762', 'Fired', '2021-04-15 20:21:05');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('0612459902', null, '8104076675', '598753', 'Official', '["ScreenControl", "ScreenView"]', '818C190ABF1101D190C2962645CDEC2F', '3465562762', 'Fired', '2021-04-15 20:19:16');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('0644906320', null, '8104076675', '709548', 'Official', '["ScreenControl", "ScreenView"]', '818C190ABF1101D190C2962645CDEC2F', '3465562762', 'Fired', '2021-04-15 20:00:41');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('1020469262', null, '9491321164', '12346', 'Guest', '["ScreenControl"]', '2B2C3BA272DF54DCD0E2B0BF0E5A5B54', null, 'Fired', '2021-04-14 17:20:51');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('1424315479', null, '8104076675', '709548', 'Official', '["ScreenControl", "ScreenView"]', '818C190ABF1101D190C2962645CDEC2F', null, 'Fired', '2021-04-15 19:56:01');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('1515136994', null, '8104076675', '852886', 'Guest', '["ScreenControl", "ScreenView", "Camera", "GPS", "FileManage"]', '818C190ABF1101D190C2962645CDEC2F', '3465562762', 'Fired', '2021-04-15 19:35:03');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('1726238686', null, '8104076675', '658899', 'Official', '["ScreenControl", "ScreenView"]', '818C190ABF1101D190C2962645CDEC2F', null, 'Fired', '2021-04-15 19:50:01');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('1743697966', null, '3465562762', '123456', 'Official', '["ScreenControl", "ScreenView", "Camera", "FileManage", "GPS"]', 'A1E5987396F4D7C4A23222B95D99E28E', null, 'Fired', '2021-04-14 20:21:10');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('1803540646', null, '8104076675', '464649', 'Official', '["ScreenControl", "ScreenView", "Camera", "FileManage", "GPS"]', '818C190ABF1101D190C2962645CDEC2F', null, 'Fired', '2021-04-15 15:23:26');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('1831066334', null, '8104076675', '709548', 'Official', '["ScreenControl", "ScreenView"]', '818C190ABF1101D190C2962645CDEC2F', '3465562762', 'Used', '2021-04-15 20:39:08');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('1854570299', null, '8104076675', '838686', 'Official', '["ScreenControl", "ScreenView", "Camera"]', '818C190ABF1101D190C2962645CDEC2F', null, 'Fired', '2021-04-15 15:27:33');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('1915959463', null, '9849194630', '123', 'Guest', '["ScreenControl", "ScreenView", "Camera", "FileManage", "GPS"]', '818C190ABF1101D190C2962645CDEC2F', '9491321164', 'Fired', '2021-04-14 17:35:47');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('1944777023', null, '3465562762', '123456', 'Official', '["ScreenControl", "ScreenView", "Camera", "FileManage", "GPS"]', 'A1E5987396F4D7C4A23222B95D99E28E', null, 'Fired', '2021-04-14 20:21:08');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('2358828800', null, '3465562762', '123456', 'Guest', '["ScreenControl"]', 'A1E5987396F4D7C4A23222B95D99E28E', null, 'Fired', '2021-04-14 20:21:02');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('2371760007', null, '8104076675', '669985', 'Official', '["ScreenControl", "ScreenView"]', '818C190ABF1101D190C2962645CDEC2F', '3465562762', 'Fired', '2021-04-15 20:03:01');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('2411349991', null, '8104076675', '709548', 'Official', '["ScreenControl", "ScreenView"]', '818C190ABF1101D190C2962645CDEC2F', '3465562762', 'Fired', '2021-04-15 19:57:50');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('2422105061', null, '9491321164', '123456', 'Guest', '["ScreenControl", "ScreenView", "Camera", "FileManage", "GPS"]', '2B2C3BA272DF54DCD0E2B0BF0E5A5B54', '9849194630', 'Fired', '2021-04-14 17:20:54');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('2958012587', null, '8104076675', '646644', 'Official', '["ScreenControl", "ScreenView", "Camera", "FileManage", "GPS"]', '818C190ABF1101D190C2962645CDEC2F', null, 'Fired', '2021-04-15 00:19:59');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('3019875336', null, '8104076675', '666666', 'Official', '["ScreenControl", "ScreenView"]', '818C190ABF1101D190C2962645CDEC2F', null, 'Fired', '2021-04-15 20:21:30');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('3058963045', null, '9849194630', '5', 'Guest', '["ScreenControl"]', '818C190ABF1101D190C2962645CDEC2F', null, 'Fired', '2021-04-14 17:32:32');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('3661018044', null, '8104076675', '925959', 'Official', '["ScreenControl", "ScreenView"]', '818C190ABF1101D190C2962645CDEC2F', '3465562762', 'Fired', '2021-04-15 19:54:38');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('3774281057', null, '3465562762', '123456', 'Guest', '["ScreenControl", "ScreenView", "Camera", "GPS", "FileManage"]', 'A1E5987396F4D7C4A23222B95D99E28E', '9849194630', 'Fired', '2021-04-14 17:05:51');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('3997030143', null, '9849194630', '123', 'Guest', '["ScreenControl"]', '818C190ABF1101D190C2962645CDEC2F', '9491321164', 'Fired', '2021-04-14 17:43:53');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('4241126683', null, '9491321164', '123', 'Guest', '["ScreenControl", "ScreenView", "Camera", "FileManage", "GPS"]', '2B2C3BA272DF54DCD0E2B0BF0E5A5B54', '9849194630', 'Fired', '2021-04-14 20:03:48');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('4469150034', null, '8104076675', '568725', 'Official', '["ScreenControl", "ScreenView"]', '818C190ABF1101D190C2962645CDEC2F', '3465562762', 'Fired', '2021-04-15 19:48:03');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('4610881682', null, '3465562762', '1', 'Guest', '["ScreenControl"]', 'A1E5987396F4D7C4A23222B95D99E28E', null, 'Fired', '2021-04-15 19:48:46');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('4661436141', null, '9849194630', '1', 'Guest', '["ScreenControl"]', '818C190ABF1101D190C2962645CDEC2F', '9491321164', 'Fired', '2021-04-14 18:07:03');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('4685530723', null, '3465562762', '123456', 'Guest', '["ScreenControl", "ScreenView"]', 'A1E5987396F4D7C4A23222B95D99E28E', '8104076675', 'Fired', '2021-04-14 20:21:05');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('4694175515', null, '9849194630', '1', 'Guest', '["ScreenControl"]', '818C190ABF1101D190C2962645CDEC2F', '9491321164', 'Fired', '2021-04-14 19:10:51');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('4784698957', null, '9849194630', '1', 'Guest', '["ScreenControl"]', '818C190ABF1101D190C2962645CDEC2F', '9491321164', 'Fired', '2021-04-14 17:53:30');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('5063446896', null, '3465562762', '356899', 'Guest', '["ScreenControl"]', 'A1E5987396F4D7C4A23222B95D99E28E', null, 'Fired', '2021-04-14 20:02:53');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('5143884478', null, '8104076675', '933939', 'Official', '["ScreenControl", "ScreenView"]', '818C190ABF1101D190C2962645CDEC2F', '3465562762', 'Fired', '2021-04-15 19:52:04');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('5432668516', null, '9849194630', '123', 'Guest', '["ScreenControl"]', '818C190ABF1101D190C2962645CDEC2F', '9491321164', 'Fired', '2021-04-14 18:05:48');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('6022631506', null, '8104076675', '664649', 'Official', '["ScreenControl", "ScreenView", "Camera", "FileManage", "GPS"]', '818C190ABF1101D190C2962645CDEC2F', null, 'Fired', '2021-04-14 23:01:06');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('6059970893', null, '3465562762', '123456', 'Official', '["ScreenControl", "ScreenView", "Camera", "GPS", "FileManage"]', 'A1E5987396F4D7C4A23222B95D99E28E', '9491321164', 'Fired', '2021-04-14 17:32:08');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('6294425892', null, '5292325892', '709548', 'Guest', '["ScreenView"]', 'A25AE004EADF95406855FFBAD5653993', '3465562762', 'Fired', '2021-04-14 03:30:33');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('6652246405', null, '8104076675', '66', 'Official', '["ScreenControl", "ScreenView"]', '818C190ABF1101D190C2962645CDEC2F', '3465562762', 'Fired', '2021-04-15 20:29:42');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('6750687450', null, '9849194630', '123', 'Guest', '["GPS"]', '818C190ABF1101D190C2962645CDEC2F', null, 'Fired', '2021-04-14 17:32:34');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('6876602085', null, '8104076675', '828686', 'Official', '["ScreenControl", "ScreenView", "Camera", "FileManage", "GPS"]', '818C190ABF1101D190C2962645CDEC2F', '3465562762', 'Fired', '2021-04-15 15:22:52');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('6887952548', 'Xiaomi/MI 9 Alpha', '8104076675', '123456', 'Official', '["ScreenControl", "ScreenView", "Camera", "FileManage", "GPS"]', '818C190ABF1101D190C2962645CDEC2F', '3465562762', 'Fired', '2021-04-14 20:26:14');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('6968332133', null, '8104076675', '282888', 'Official', '["ScreenControl", "ScreenView"]', '818C190ABF1101D190C2962645CDEC2F', '3465562762', 'Fired', '2021-04-15 19:44:28');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('7233060596', null, '9849194630', '123', 'Guest', '["ScreenControl"]', '818C190ABF1101D190C2962645CDEC2F', null, 'Fired', '2021-04-14 17:23:11');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('7548368309', null, '9849194630', '123', 'Guest', '["ScreenControl"]', '818C190ABF1101D190C2962645CDEC2F', '9491321164', 'Fired', '2021-04-14 20:03:36');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('7580278936', null, '8104076675', '935383', 'Official', '["ScreenControl", "ScreenView"]', '818C190ABF1101D190C2962645CDEC2F', '3465562762', 'Fired', '2021-04-15 15:27:31');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('7645088524', null, '8104076675', '688688', 'Official', '["ScreenControl", "ScreenView"]', '818C190ABF1101D190C2962645CDEC2F', '3465562762', 'Fired', '2021-04-15 19:41:24');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('7916479381', null, '3465562762', '555555', 'Guest', '["ScreenControl", "ScreenView", "Camera", "FileManage", "GPS"]', 'A1E5987396F4D7C4A23222B95D99E28E', '9491321164', 'Fired', '2021-04-14 19:04:02');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('8006471169', null, '9491321164', '123456', 'Guest', '["ScreenControl", "ScreenView", "Camera", "FileManage", "GPS"]', '2B2C3BA272DF54DCD0E2B0BF0E5A5B54', '9849194630', 'Fired', '2021-04-14 17:15:07');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('8071665034', null, '9491321164', '123', 'Guest', '["ScreenControl"]', '2B2C3BA272DF54DCD0E2B0BF0E5A5B54', null, 'Fired', '2021-04-14 19:23:59');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('8155045638', null, '8104076675', '358996', 'Official', '["ScreenControl", "ScreenView", "Camera", "FileManage", "GPS"]', '818C190ABF1101D190C2962645CDEC2F', '3465562762', 'Fired', '2021-04-15 00:40:07');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('8399939129', null, '8104076675', '868258', 'Official', '["ScreenControl", "ScreenView", "Camera", "FileManage", "GPS"]', '818C190ABF1101D190C2962645CDEC2F', null, 'Fired', '2021-04-15 15:24:39');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('9177858643', 'HUAWEI/L10 Android10', '9491321164', '123456', 'Official', '["ScreenControl", "ScreenView", "Camera", "FileManage", "GPS"]', '2B2C3BA272DF54DCD0E2B0BF0E5A5B54', '3465562762', 'Fired', '2021-04-14 17:13:47');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('9238399697', null, '3465562762', '123456', 'Official', '["ScreenControl", "ScreenView", "Camera", "GPS", "FileManage"]', 'A1E5987396F4D7C4A23222B95D99E28E', null, 'Fired', '2021-04-14 20:02:51');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('9349193210', null, '8104076675', '399969', 'Guest', '["ScreenControl"]', '818C190ABF1101D190C2962645CDEC2F', null, 'Fired', '2021-04-15 00:40:16');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('9352555175', null, '9491321164', '123', 'Guest', '["ScreenControl", "ScreenView", "Camera", "FileManage", "GPS"]', '2B2C3BA272DF54DCD0E2B0BF0E5A5B54', '9849194630', 'Fired', '2021-04-14 19:23:35');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('9375424150', null, '8104076675', '686838', 'Official', '["ScreenControl", "ScreenView", "Camera", "GPS", "FileManage"]', '818C190ABF1101D190C2962645CDEC2F', '3465562762', 'Fired', '2021-04-15 01:04:27');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('9584931740', null, '9849194630', '123', 'Guest', '["ScreenControl"]', '818C190ABF1101D190C2962645CDEC2F', '9491321164', 'Fired', '2021-04-14 17:54:56');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('9600990721', null, '3465562762', '669985', 'Official', '["ScreenControl", "ScreenView", "Camera", "GPS", "FileManage"]', 'A1E5987396F4D7C4A23222B95D99E28E', null, 'Fired', '2021-04-14 20:02:48');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('9604304464', null, '8104076675', '996464', 'Official', '["ScreenControl", "ScreenView", "Camera", "FileManage", "GPS"]', '818C190ABF1101D190C2962645CDEC2F', null, 'Fired', '2021-04-14 22:58:50');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('9739782290', null, '8104076675', '66666', 'Official', '["ScreenControl", "ScreenView"]', '818C190ABF1101D190C2962645CDEC2F', '3465562762', 'Fired', '2021-04-15 20:17:34');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('9801413614', null, '8104076675', '709548', 'Official', '["ScreenControl", "ScreenView"]', '818C190ABF1101D190C2962645CDEC2F', '3465562762', 'Fired', '2021-04-15 20:05:54');
INSERT INTO t_assist (vid, alias, uid, secret, access, permissions, device_id, peer_uid, status, create_time) VALUES ('9896893653', null, '9849194630', '123', 'Guest', '["ScreenControl"]', '818C190ABF1101D190C2962645CDEC2F', '9491321164', 'Fired', '2021-04-14 18:01:49');