package com.kirshi.freya.server.socket.packet;

import com.xuhao.didi.core.iocore.interfaces.ISendable;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:BaseProtoPacket.java
 * @LastModified:2021-03-27T01:09:45.778+08:00
 */

public class BaseProtoPacket implements ISendable {
    private byte[] body;

    public  BaseProtoPacket(byte[] body){
        this.body = body;
    }

    @Override
    public final byte[] parse() {
        ByteBuffer bb = ByteBuffer.allocate(4 + body.length);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putInt(body.length);
        bb.put(body);
        return bb.array();
    }
}
