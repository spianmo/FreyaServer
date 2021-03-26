package com.kirshi.freya.server.socket.packet;

import com.xuhao.didi.core.iocore.interfaces.ISendable;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Copyright (c) 2021  Spianmo, Inc. All rights reserved.
 * @Project TrackerServer
 * @Author Finger
 * @FileName BaseProtoPacket.java
 * @LastModified 2021-03-04 22:56:49
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
