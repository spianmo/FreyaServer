package com.kirshi.freya.server.socket.pojo;

import com.kirshi.protocol.HandShakeProto;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.server.IClient;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:ClientInfoBean.java
 * @LastModified:2021-03-27T01:09:45.782+08:00
 */

public class ClientInfoBean {
    private long mCreateTime;
    private long mHandShakeTime;
    private IClient mIClient;
    private String mScode;
    private String mUid;
    private String mUniqueTag;
    private HandShakeProto.Type mType;
    private IClient mPeerIClient;
    private String mPeerUniqueTag;
    private boolean mIsConnected;
    private long mLastActionTime;


    public ClientInfoBean(IClient IClient) {
        mIClient = IClient;
    }

    public long getCreateTime() {
        return mCreateTime;
    }

    public void setCreateTime(long createTime) {
        mCreateTime = createTime;
    }

    public long getHandShakeTime() {
        return mHandShakeTime;
    }

    public void setHandShakeTime(long handShakeTime) {
        mHandShakeTime = handShakeTime;
    }

    public IClient getIClient() {
        return mIClient;
    }

    public long getLastActionTime() {
        return mLastActionTime;
    }

    public void setLastActionTime(long lastActionTime) {
        mLastActionTime = lastActionTime;
    }

    public String getScode() {
        return mScode;
    }

    public void setScode(String scode) {
        this.mScode = scode;
    }

    public String getUid() {
        return mUid;
    }

    public void setUid(String uid) {
        this.mUid = uid;
    }

    public String getUniqueTag() {
        return mUniqueTag;
    }

    public void setUniqueTag(String uniqueTag) {
        mUniqueTag = uniqueTag;
    }

    public HandShakeProto.Type getType() {
        return mType;
    }

    public void setType(HandShakeProto.Type type) {
        this.mType = type;
    }


    public boolean isConnected() {
        return mIsConnected;
    }

    public void setConnected(boolean connected) {
        this.mIsConnected = connected;
    }

    public IClient getPeerIClient() {
        return mPeerIClient;
    }

    public void setPeerIClient(IClient mPeerIClient) {
        this.mPeerIClient = mPeerIClient;
    }

    public String getPeerUniqueTag() {
        return mPeerUniqueTag;
    }

    public void setPeerUniqueTag(String mPeerUniqueTag) {
        this.mPeerUniqueTag = mPeerUniqueTag;
    }

}
