package com.kirshi.freya.server.socket;

import com.kirshi.freya.server.socket.pojo.ClientInfoBean;
import com.xuhao.didi.socket.common.interfaces.basic.AbsLoopThread;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright (c) 2021  Spianmo, Inc. All rights reserved.
 * @Project TrackerServer
 * @Author Finger
 * @FileName WatchdogThread.java
 * @LastModified 2021-03-04 22:56:49
 */

public class WatchdogThread extends AbsLoopThread {


    private static final long ONE_MINUTE = 10 * 1000;

    private ConcurrentHashMap<String, ClientInfoBean> mMap = null;

    public WatchdogThread(ConcurrentHashMap<String, ClientInfoBean> map) {
        mMap = map;
    }

    @Override
    protected void beforeLoop() throws Exception {
        super.beforeLoop();
    }

    @Override
    protected void runInLoopThread() throws Exception {
        HashMap<String, ClientInfoBean> tempMap = new HashMap<>();
        tempMap.putAll(mMap);
        long nowMills = System.currentTimeMillis();
        Iterator<String> it = tempMap.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            ClientInfoBean bean = tempMap.get(key);
            long lastMills = bean.getLastActionTime();
            long handshakeMills = bean.getHandShakeTime();
            long createMills = bean.getCreateTime();
            if (lastMills == 0) {
                lastMills = handshakeMills;
                if (handshakeMills == 0) {
                    lastMills = createMills;
                }
            }
            long timeDiffer = nowMills - lastMills;
            if (timeDiffer > ONE_MINUTE) {//大于1分钟了表明已死
                bean.getIClient().disconnect(new RuntimeException("超时断开.超时时间为:" + timeDiffer / ONE_MINUTE + "分钟"));
                System.out.println("超时断开.超时时间为:" + timeDiffer / ONE_MINUTE + "分钟");
                it.remove();
            }
        }
        Thread.sleep(10 * 1000);//每10秒一次检查
    }

    @Override
    protected void loopFinish(Exception e) {
        if (e != null) {
            e.printStackTrace();
        }
    }
}
