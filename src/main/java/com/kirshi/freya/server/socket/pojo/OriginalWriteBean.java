package com.kirshi.freya.server.socket.pojo;

import com.xuhao.didi.core.iocore.interfaces.ISendable;
import com.xuhao.didi.core.pojo.OriginalData;

import java.util.Arrays;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:OriginalWriteBean.java
 * @LastModified:2021-03-27T01:09:45.788+08:00
 */

public class OriginalWriteBean implements ISendable {
    private OriginalData mOriginalData;

    public OriginalWriteBean(OriginalData originalData) {
        mOriginalData = originalData;
    }

    @Override
    public byte[] parse() {
        byte[] head = mOriginalData.getHeadBytes();
        byte[] body = mOriginalData.getBodyBytes();
        int headLength = head.length;
        int bodyLength = body.length;

        head = Arrays.copyOf(head, headLength + bodyLength);
        System.arraycopy(body, 0, head, headLength, bodyLength);
        return head;
    }
}
