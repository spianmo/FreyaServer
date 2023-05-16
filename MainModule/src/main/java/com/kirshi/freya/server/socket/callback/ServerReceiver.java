package com.kirshi.freya.server.socket.callback;

import com.google.protobuf.Any;
import com.kirshi.freya.server.config.RedisUtil;
import com.kirshi.freya.server.constant.RedisConstant;
import com.kirshi.freya.server.socket.WatchdogThread;
import com.kirshi.freya.server.socket.packet.BaseProtoPacket;
import com.kirshi.freya.server.socket.pojo.ClientInfoBean;
import com.kirshi.freya.server.socket.pojo.MsgWriteBean;
import com.kirshi.freya.server.socket.utils.Log;
import com.kirshi.freya.server.utils.SpringContextUtils;
import com.kirshi.protocol.CmdProto;
import com.kirshi.protocol.CommandProto;
import com.kirshi.protocol.HandShakeProto;
import com.xuhao.didi.socket.client.sdk.OkSocket;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.server.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright (c) 2023
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:ServerReceiver.java
 * @LastModified:2023-05-16T02:29:41.438+08:00
 */

public class ServerReceiver implements IServerActionListener {

    RedisUtil redis = SpringContextUtils.getBean(RedisUtil.class);
    private volatile IServerManager mIServerManager;

    private volatile int mPort;

    public static ConcurrentHashMap<String, ClientInfoBean> mClientInfoBeanList = new ConcurrentHashMap<>();

    private WatchdogThread mWatchdogThread = null;


    public ServerReceiver(int port) {
        mPort = port;
    }

    public void setServerManager(IServerManager serverManager) {
        mIServerManager = serverManager;
    }

    @Override
    public void onServerListening(int serverPort) {
        mClientInfoBeanList.clear();
        if (mWatchdogThread != null && !mWatchdogThread.isShutdown()) {
            mWatchdogThread.shutdown();
        }
        Log.i("服务器启动完成.正在监听端口:" + serverPort);
        mWatchdogThread = new WatchdogThread(mClientInfoBeanList);
        mWatchdogThread.start();
    }

    @Override
    public void onClientConnected(IClient client, int serverPort, IClientPool clientPool) {
        ClientInfoBean bean = new ClientInfoBean(client);
        bean.setUniqueTag(client.getUniqueTag());
        bean.setCreateTime(System.currentTimeMillis());
        client.addIOCallback(new ClientIOCallback(bean));

        Log.i(client.getUniqueTag() + " 上线,被控端数量:" + getClientNum());
    }

    @Override
    public void onClientDisconnected(IClient client, int serverPort, IClientPool clientPool) {
        byte[] packet_offine = CommandProto.BaseCommandMessage.newBuilder().setCmd(CmdProto.CmdAction.TK_ACTION_HANDSHAKE).setData(Any.pack(HandShakeProto.HandShakeRespMessage.newBuilder().setCmd(CmdProto.CmdAction.TK_ACTION_HANDSHAKE).setMsg("被控端已离线").setStatus(HandShakeProto.HandShakeStatus.DEVICEOFFINE).build())).build().toByteArray();
        CancelLock(client);
        if (mClientInfoBeanList.get(client.getUniqueTag()) != null && mClientInfoBeanList.get(client.getUniqueTag()).getPeerIClient() != null) {
            mClientInfoBeanList.get(client.getUniqueTag()).getPeerIClient().send(new BaseProtoPacket(packet_offine));
        }
        client.removeAllIOCallback();
        if (mClientInfoBeanList.get(client.getUniqueTag()) != null && mClientInfoBeanList.get(client.getUniqueTag()).getType() == HandShakeProto.Type.CLIENT) {
            redis.setRemove(RedisConstant.SessionKeys, mClientInfoBeanList.get(client.getUniqueTag()).getScode());
        }
        mClientInfoBeanList.remove(client.getUniqueTag());
        Log.i(client.getUniqueTag() + " 下线,被控端数量:" + getClientNum());
    }

    @Override
    public void onServerWillBeShutdown(int serverPort, IServerShutdown shutdown, IClientPool clientPool, Throwable throwable) {
        clientPool.sendToAll(new MsgWriteBean("服务器即将关闭"));
        redis.del(RedisConstant.SessionKeys);
        if (throwable == null) {
            Log.i("服务器即将关闭,没有异常");
        } else {
            Log.i("服务器即将关闭,异常信息:" + throwable.getMessage());
            throwable.printStackTrace();
        }
        mWatchdogThread.shutdown();
        shutdown.shutdown();
    }

    @Override
    public void onServerAlreadyShutdown(int serverPort) {
        mClientInfoBeanList.clear();
        OkSocket.server(serverPort).unRegisterReceiver(this);
        Log.i("服务器已经关闭");
    }

    public int getClientNum() {
        int num = 0;
        Map<String, ClientInfoBean> map = new HashMap<>(mClientInfoBeanList);
        for (String key : map.keySet()) {
            if (map.get(key).getType() == HandShakeProto.Type.CLIENT) {
                num++;
            }
        }
        return num;
    }

    public void CancelLock(IClient client) {
        for (ConcurrentHashMap.Entry<String, ClientInfoBean> entry : mClientInfoBeanList.entrySet()) {
            if (client.getUniqueTag().equals(entry.getValue().getPeerUniqueTag())) {
                client.send(new BaseProtoPacket(HandShakeProto.HandShakeRespMessage.newBuilder().setStatus(HandShakeProto.HandShakeStatus.DEVICEOFFINE).setCmd(CmdProto.CmdAction.TK_ACTION_COMMAND).setMsg("被控端已离线").build().toByteArray()));
                entry.getValue().getIClient().send(
                        new BaseProtoPacket(
                                CommandProto.BaseCommandMessage.newBuilder()
                                        .setCmd(CmdProto.CmdAction.TK_ACTION_COMMAND)
                                        .setData(Any.pack(CommandProto.BizCommandMessage.newBuilder()
                                                .setCommand(CommandProto.CommandInfo.COMMAND_SCREENTRANS)
                                                .setExtra(Any.pack(CommandProto.ScreenTransMessage.newBuilder().setSwiStatus(false).build()))
                                                .build()))
                                        .build().toByteArray())
                );
                entry.getValue().getIClient().send(
                        new BaseProtoPacket(
                                CommandProto.BaseCommandMessage.newBuilder()
                                        .setCmd(CmdProto.CmdAction.TK_ACTION_COMMAND)
                                        .setData(Any.pack(CommandProto.BizCommandMessage.newBuilder()
                                                .setCommand(CommandProto.CommandInfo.COMMAND_AUDIOLIVE)
                                                .setExtra(Any.pack(CommandProto.AudioLiveMessage.newBuilder().setSwiStatus(false).build()))
                                                .build()))
                                        .build().toByteArray())
                );
                entry.getValue().getIClient().send(
                        new BaseProtoPacket(
                                CommandProto.BaseCommandMessage.newBuilder()
                                        .setCmd(CmdProto.CmdAction.TK_ACTION_COMMAND)
                                        .setData(Any.pack(CommandProto.BizCommandMessage.newBuilder()
                                                .setCommand(CommandProto.CommandInfo.COMMAND_CAMERALIVE)
                                                .setExtra(Any.pack(CommandProto.CameraLiveMessage.newBuilder().setCameraAction(CommandProto.CameraAction.STOP).build()))
                                                .build()))
                                        .build().toByteArray())
                );
                Log.i("======> 主控端离线，已向被控发送stream关闭命令");
                entry.getValue().setPeerUniqueTag("");
                entry.getValue().setPeerIClient(null);
                entry.getValue().setConnected(false);
            }
        }
    }

}
