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

INSERT INTO t_user (uid, account, passwd, nickname, openid, gender, reg_time) VALUES ('3465562762', '397773106', '1dd307c2b5236842b3079236248917b7', 'Finger', '6C8FAFDAB92F0082B2BD56918DA2E830', 'MAN', '2021-04-09 13:45:06');
INSERT INTO t_user (uid, account, passwd, nickname, openid, gender, reg_time) VALUES ('5292325892', '2158967257', '1dd307c2b5236842b3079236248917b7', 'Kyouha', '60BE5AB3E27F5DBE875906BFA22F859D', 'WOMAN', '2021-04-09 17:04:52');
INSERT INTO t_user (uid, account, passwd, nickname, openid, gender, reg_time) VALUES ('8104076675', '1554424537', '1dd307c2b5236842b3079236248917b7', 'ptrace', 'F38D5D7E7BF81A9DEB810540245BDCE3', 'MAN', '2021-04-06 18:03:44');
INSERT INTO t_user (uid, account, passwd, nickname, openid, gender, reg_time) VALUES ('9491321164', '1289037748', '1dd307c2b5236842b3079236248917b7', '朝', 'FD9D525B5FC0D3FB2C23B17252061737', 'WOMAN', '2021-04-12 16:55:12');
INSERT INTO t_user (uid, account, passwd, nickname, openid, gender, reg_time) VALUES ('9849194630', '3038073959', '8c0afb19e024268995244a1a5569ea88', '啊嘞', '367BAF6F3362D1EE028C48EEB353C91E', 'MAN', '2021-04-14 16:47:00');