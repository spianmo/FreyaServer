package com.kirshi.freya.server.socket.pojo;


import com.xuhao.didi.core.iocore.interfaces.ISendable;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/**
 * Copyright (c) 2021  Spianmo, Inc. All rights reserved.
 * @Project TrackerServer
 * @Author Finger
 * @FileName MsgWriteBean.java
 * @LastModified 2021-03-04 22:56:49
 */

/**
 * Created by Tony on 2017/10/24.
 */

public class MsgWriteBean implements ISendable {
    private String content = "";

    public MsgWriteBean(String content) {
        this.content = content;
    }

    @Override
    public byte[] parse() {
        byte[] body = content.getBytes(Charset.defaultCharset());
        ByteBuffer bb = ByteBuffer.allocate(4 + body.length);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putInt(body.length);
        bb.put(body);
        return bb.array();
    }
}
