package com.kirshi.freya.server.socket.callback;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.kirshi.protocol.BizResponseProto;
import com.kirshi.protocol.CmdProto;
import com.kirshi.protocol.CommandProto;
import com.kirshi.protocol.HandShakeProto;
import com.kirshi.freya.server.socket.packet.BaseProtoPacket;
import com.kirshi.freya.server.socket.pojo.ClientInfoBean;
import com.kirshi.freya.server.socket.utils.Log;
import com.xuhao.didi.core.iocore.interfaces.ISendable;
import com.xuhao.didi.core.pojo.OriginalData;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.server.IClient;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.server.IClientIOCallback;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.server.IClientPool;

import java.util.concurrent.ConcurrentHashMap;

import static com.kirshi.freya.server.socket.callback.ServerReceiver.mClientInfoBeanList;


/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:ClientIOCallback.java
 * @LastModified:2021-04-14T02:09:19.834+08:00
 */

public class ClientIOCallback implements IClientIOCallback {

    private boolean mNeedDisconnect;

    private ClientInfoBean mClientInfoBean;

    public ClientIOCallback(ClientInfoBean bean) {
        mClientInfoBean = bean;
        mNeedDisconnect = false;
    }

    @Override
    public void onClientRead(OriginalData originalData, IClient client, IClientPool<IClient, String> clientPool) {
        try {
            CommandProto.BaseCommandMessage msg_command = CommandProto.BaseCommandMessage.parseFrom(originalData.getBodyBytes());
            //Log.i("\n原始报文："+msg_command.toString());
            switch (msg_command.getCmd()) {
                case TK_ACTION_HANDSHAKE:
                    HandShakeProto.HandShakeMessage msg_handShake = msg_command.getData().unpack(HandShakeProto.HandShakeMessage.class);
                    mClientInfoBean.setScode(msg_handShake.getScode());
                    mClientInfoBean.setUid(msg_handShake.getUid());
                    mClientInfoBean.setType(msg_handShake.getType());
                    mClientInfoBean.setHandShakeTime(System.currentTimeMillis());
                    mClientInfoBean.setConnected(false);
                    mClientInfoBean.setUniqueTag(client.getUniqueTag());
                    if (msg_handShake.getType() == HandShakeProto.Type.CLIENT) {
                        if (!isInClientPool()) {
                            mClientInfoBeanList.put(client.getUniqueTag(), mClientInfoBean);
                            Log.i("\n被控端：" + mClientInfoBean.getUniqueTag() + "上线\nscode:" + mClientInfoBean.getScode() + "\nuid:" + mClientInfoBean.getUid() + "\n此时被控端总数为" + mClientInfoBeanList.size() + "\n");
                        }
                    } else if (msg_handShake.getType() == HandShakeProto.Type.MASTER) {
                        Log.i("======> HandShakeProto.Type.MASTER");
                        switch (doHandShake()) {
                            case SUCCESS:
                                Log.i("======> HandShakeProto.Type.MASTER 被控端连接成功");
                                byte[] packet_success = CommandProto.BaseCommandMessage.newBuilder().setCmd(CmdProto.CmdAction.TK_ACTION_HANDSHAKE).setData(Any.pack(HandShakeProto.HandShakeRespMessage.newBuilder().setCmd(CmdProto.CmdAction.TK_ACTION_HANDSHAKE).setMsg("被控端连接成功").setStatus(HandShakeProto.HandShakeStatus.SUCCESS).build())).build().toByteArray();
                                client.send(new BaseProtoPacket(packet_success));
                                break;
                            case DEVICEBUSY:
                                Log.i("======> HandShakeProto.Type.MASTER 被控端已被占线");
                                byte[] packet_busy = CommandProto.BaseCommandMessage.newBuilder().setCmd(CmdProto.CmdAction.TK_ACTION_HANDSHAKE).setData(Any.pack(HandShakeProto.HandShakeRespMessage.newBuilder().setCmd(CmdProto.CmdAction.TK_ACTION_HANDSHAKE).setMsg("被控端已被占线").setStatus(HandShakeProto.HandShakeStatus.DEVICEBUSY).build())).build().toByteArray();
                                client.send(new BaseProtoPacket(packet_busy));
                                break;
                            case DEVICEOFFINE:
                                Log.i("======> HandShakeProto.Type.MASTER 被控端不在线");
                                byte[] packet_offine = CommandProto.BaseCommandMessage.newBuilder().setCmd(CmdProto.CmdAction.TK_ACTION_HANDSHAKE).setData(Any.pack(HandShakeProto.HandShakeRespMessage.newBuilder().setCmd(CmdProto.CmdAction.TK_ACTION_HANDSHAKE).setMsg("被控端不在线").setStatus(HandShakeProto.HandShakeStatus.DEVICEOFFINE).build())).build().toByteArray();
                                client.send(new BaseProtoPacket(packet_offine));
                                break;
                        }
                    }
                    mClientInfoBean.setHandShakeTime(System.currentTimeMillis());
                    break;
                case TK_ACTION_KICK:
                    break;
                case TK_ACTION_RESTART_SERVER:
                    break;
                case TK_ACTION_HEARTBEAT:
                    client.send(new BaseProtoPacket(originalData.getBodyBytes()));
                    break;
                case TK_ACTION_COMMAND:
                    //Log.i("======> TK_ACTION_COMMAND " + CommandProto.BizCommandMessage.parseFrom(originalData.getBodyBytes()).getCommand().toString());
                    SendToClient(new BaseProtoPacket(originalData.getBodyBytes()));
                    break;
                case TK_ACTION_COMMAND_RESULT:
                    Log.i("======> TK_ACTION_COMMAND_RESULT " + BizResponseProto.ResponseMessage.parseFrom(originalData.getBodyBytes()).getCommand().toString());
                    Log.i("======> DATA: " + BizResponseProto.ResponseMessage.parseFrom(originalData.getBodyBytes()).toString());
                    SendToMaster(new BaseProtoPacket(originalData.getBodyBytes()));
                    break;
                default:
                    client.disconnect();
                    break;
            }
            mClientInfoBean.setLastActionTime(System.currentTimeMillis());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClientWrite(ISendable sendable, IClient client, IClientPool<IClient, String> clientPool) {
        mClientInfoBean.setLastActionTime(System.currentTimeMillis());
        if (mNeedDisconnect) {
            client.disconnect(new Exception("======> 主动断开,因为判定非法"));
        }
    }

    private boolean isInClientPool() {
        for (ConcurrentHashMap.Entry<String, ClientInfoBean> entry : mClientInfoBeanList.entrySet()) {
            return entry.getValue().getUniqueTag().equals(mClientInfoBean.getIClient().getUniqueTag());
        }
        return false;
    }

    enum HandShakeStatus {
        DEVICEBUSY, SUCCESS, DEVICEOFFINE
    }

    private HandShakeStatus doHandShake() {
        for (ConcurrentHashMap.Entry<String, ClientInfoBean> entry : mClientInfoBeanList.entrySet()) {
            if (entry.getValue().getScode().equals(mClientInfoBean.getScode()) && !entry.getValue().getUniqueTag().equals(mClientInfoBean.getUniqueTag())) {
                if (!entry.getValue().isConnected()) {
                    entry.getValue().setPeerIClient(mClientInfoBean.getIClient());
                    entry.getValue().setPeerUniqueTag(mClientInfoBean.getUniqueTag());
                    entry.getValue().setConnected(true);
                    return HandShakeStatus.SUCCESS;
                } else {
                    return HandShakeStatus.DEVICEBUSY;
                }
            }
        }
        return HandShakeStatus.DEVICEOFFINE;
    }

    private void SendToClient(ISendable packet) {
        for (ConcurrentHashMap.Entry<String, ClientInfoBean> entry : mClientInfoBeanList.entrySet()) {
            if (mClientInfoBean.getUniqueTag().equals(entry.getValue().getPeerUniqueTag())) {
                entry.getValue().getIClient().send(packet);
            }
        }
    }

    private void SendToMaster(ISendable packet) {
        Log.i("====> SendToMaster\n" + mClientInfoBean.getPeerUniqueTag() + "\n" + mClientInfoBean.getType().toString() + "\n" + mClientInfoBean.getUniqueTag());
        for (ConcurrentHashMap.Entry<String, ClientInfoBean> entry : mClientInfoBeanList.entrySet()) {
            Log.i("====> 遍历一次" + mClientInfoBean.getUniqueTag() + " " + entry.getValue().getUniqueTag());
            if (mClientInfoBean.getUniqueTag().equals(entry.getValue().getUniqueTag())) {
                if (mClientInfoBean.isConnected()) {
                    Log.i("====> 根据Client " + mClientInfoBean.getUniqueTag() + " 匹配到对端并发送给Master " + entry.getValue().getPeerIClient().getUniqueTag());
                    entry.getValue().getPeerIClient().send(packet);
                }
            }
        }
    }
}
