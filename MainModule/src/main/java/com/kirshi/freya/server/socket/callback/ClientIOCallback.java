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
 *
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
                    HandShakeProto.HandShakeMessage handShakeMessage = msg_command.getData().unpack(HandShakeProto.HandShakeMessage.class);
                    mClientInfoBean.setScode(handShakeMessage.getScode());
                    mClientInfoBean.setUid(handShakeMessage.getUid());
                    mClientInfoBean.setType(handShakeMessage.getType());
                    mClientInfoBean.setHandShakeTime(System.currentTimeMillis());
                    mClientInfoBean.setConnected(false);
                    mClientInfoBean.setUniqueTag(client.getUniqueTag());
                    if (handShakeMessage.getType() == HandShakeProto.Type.CLIENT) {
                        if (!isInClientPool()) {
                            mClientInfoBeanList.put(client.getUniqueTag(), mClientInfoBean);
                            Log.i("\n被控端：" + mClientInfoBean.getUniqueTag() + "上线\nscode:" + mClientInfoBean.getScode() + "\nuid:" + mClientInfoBean.getUid() + "\n此时被控端总数为" + mClientInfoBeanList.size() + "\n");
                        }
                    } else if (handShakeMessage.getType() == HandShakeProto.Type.MASTER) {
                        Log.i("======> HandShakeProto.Type.MASTER");
                        switch (doHandShake()) {
                            case SUCCESS:
                                Log.i("======> HandShakeProto.Type.MASTER 被控端连接成功");
                                byte[] packet_success = CommandProto.BaseCommandMessage.newBuilder().setCmd(CmdProto.CmdAction.TK_ACTION_HANDSHAKE).setData(Any.pack(HandShakeProto.HandShakeRespMessage.newBuilder().setCmd(CmdProto.CmdAction.TK_ACTION_HANDSHAKE).setMsg("被控端连接成功").setStatus(HandShakeProto.HandShakeStatus.SUCCESS).build())).build().toByteArray();
                                client.send(new BaseProtoPacket(packet_success));
                                break;
                            case DEVICE_BUSY:
                                Log.i("======> HandShakeProto.Type.MASTER 被控端已被占线");
                                byte[] packet_busy = CommandProto.BaseCommandMessage.newBuilder().setCmd(CmdProto.CmdAction.TK_ACTION_HANDSHAKE).setData(Any.pack(HandShakeProto.HandShakeRespMessage.newBuilder().setCmd(CmdProto.CmdAction.TK_ACTION_HANDSHAKE).setMsg("被控端已被占线").setStatus(HandShakeProto.HandShakeStatus.DEVICEBUSY).build())).build().toByteArray();
                                client.send(new BaseProtoPacket(packet_busy));
                                break;
                            case DEVICE_OFFLINE:
                                Log.i("======> HandShakeProto.Type.MASTER 被控端不在线");
                                byte[] packet_offline = CommandProto.BaseCommandMessage.newBuilder().setCmd(CmdProto.CmdAction.TK_ACTION_HANDSHAKE).setData(Any.pack(HandShakeProto.HandShakeRespMessage.newBuilder().setCmd(CmdProto.CmdAction.TK_ACTION_HANDSHAKE).setMsg("被控端不在线").setStatus(HandShakeProto.HandShakeStatus.DEVICEOFFINE).build())).build().toByteArray();
                                client.send(new BaseProtoPacket(packet_offline));
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
//                    Log.i("======> TK_ACTION_COMMAND " + CommandProto.BaseCommandMessage
//                            .parseFrom(originalData.getBodyBytes())
//                            .getData()
//                            .unpack(BizResponseProto.ResponseMessage.class)
//                            .getCommand());
                    sendToClient(new BaseProtoPacket(originalData.getBodyBytes()));
                    break;
                case TK_ACTION_COMMAND_RESULT:
                    Log.i("======> TK_ACTION_COMMAND_RESULT " + CommandProto.BaseCommandMessage
                            .parseFrom(originalData.getBodyBytes())
                            .getData()
                            .unpack(BizResponseProto.ResponseMessage.class)
                            .getCommand());
                    sendToMaster(new BaseProtoPacket(originalData.getBodyBytes()));
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
        DEVICE_BUSY, SUCCESS, DEVICE_OFFLINE
    }

    /**
     * 主控与被控握手
     *
     * @return
     */
    private HandShakeStatus doHandShake() {
        for (ConcurrentHashMap.Entry<String, ClientInfoBean> entry : mClientInfoBeanList.entrySet()) {
            if (entry.getValue().getScode().equals(mClientInfoBean.getScode()) && !entry.getValue().getUniqueTag().equals(mClientInfoBean.getUniqueTag())) {
                if (!entry.getValue().isConnected()) {
                    entry.getValue().setPeerIClient(mClientInfoBean.getIClient());
                    entry.getValue().setPeerUniqueTag(mClientInfoBean.getUniqueTag());
                    entry.getValue().setConnected(true);
                    mClientInfoBean.setPeerIClient(entry.getValue().getIClient());
                    mClientInfoBean.setPeerUniqueTag(entry.getValue().getUniqueTag());
                    mClientInfoBean.setConnected(true);
                    return HandShakeStatus.SUCCESS;
                } else {
                    return HandShakeStatus.DEVICE_BUSY;
                }
            }
        }
        return HandShakeStatus.DEVICE_OFFLINE;
    }

    /**
     * 主控发包给被控，遍历被控列表匹配对端的UniqueTag，如果和主控的UniqueTag相同则取出被控Client对象发包
     *
     * @param packet
     */
    private void sendToClient(ISendable packet) {
        mClientInfoBean.getPeerIClient().send(packet);
    }

    /**
     * 被控发包给主控，遍历被控列表匹配对端的UniqueTag，如果和被控的UniqueTag相同则取出主控Client对象发包
     *
     * @param packet
     */
    private void sendToMaster(ISendable packet) {
        ClientInfoBean clientInfo = mClientInfoBeanList.get(mClientInfoBean.getUniqueTag());
        if (clientInfo == null) return;
        if (!clientInfo.isConnected()) {
            sendCloseSignal(clientInfo);
        }
        if (clientInfo.getPeerIClient() != null) {
            clientInfo.getPeerIClient().send(packet);
        }
    }

    /**
     * 关闭实时流推送
     * @param clientInfo
     */
    public static void sendCloseSignal(ClientInfoBean clientInfo) {
        clientInfo.getIClient().send(
                new BaseProtoPacket(
                        CommandProto.BaseCommandMessage.newBuilder()
                                .setCmd(CmdProto.CmdAction.TK_ACTION_COMMAND)
                                .setData(Any.pack(CommandProto.BizCommandMessage.newBuilder()
                                        .setCommand(CommandProto.CommandInfo.COMMAND_SCREENTRANS)
                                        .setExtra(Any.pack(CommandProto.ScreenTransMessage.newBuilder().setSwiStatus(false).build()))
                                        .build()))
                                .build().toByteArray())
        );
        clientInfo.getIClient().send(
                new BaseProtoPacket(
                        CommandProto.BaseCommandMessage.newBuilder()
                                .setCmd(CmdProto.CmdAction.TK_ACTION_COMMAND)
                                .setData(Any.pack(CommandProto.BizCommandMessage.newBuilder()
                                        .setCommand(CommandProto.CommandInfo.COMMAND_AUDIOLIVE)
                                        .setExtra(Any.pack(CommandProto.AudioLiveMessage.newBuilder().setSwiStatus(false).build()))
                                        .build()))
                                .build().toByteArray())
        );
        clientInfo.getIClient().send(
                new BaseProtoPacket(
                        CommandProto.BaseCommandMessage.newBuilder()
                                .setCmd(CmdProto.CmdAction.TK_ACTION_COMMAND)
                                .setData(Any.pack(CommandProto.BizCommandMessage.newBuilder()
                                        .setCommand(CommandProto.CommandInfo.COMMAND_CAMERALIVE)
                                        .setExtra(Any.pack(CommandProto.CameraLiveMessage.newBuilder().setCameraAction(CommandProto.CameraAction.STOP).build()))
                                        .build()))
                                .build().toByteArray())
        );
    }
}
