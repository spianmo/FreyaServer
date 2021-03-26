package com.kirshi.freya.server;

import com.kirshi.freya.server.socket.callback.ServerReceiver;
import com.kirshi.freya.server.socket.utils.IPUtil;
import com.kirshi.freya.server.socket.utils.Log;
import com.xuhao.didi.core.utils.SLog;
import com.xuhao.didi.socket.client.sdk.OkSocket;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.server.IServerManager;
import com.xuhao.didi.socket.server.impl.OkServerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Finger
 */
@SpringBootApplication
public class FreyaServerApplication {

    public static Logger logger;
    private static final int SOCKET_PORT = 9089;

    public static void main(String[] args) {
        logger = LoggerFactory.getLogger(FreyaServerApplication.class);
        SpringApplication.run(FreyaServerApplication.class, args);

        Log.i("系统环境："+System.getProperties().getProperty("os.name")+" "+System.getProperties().getProperty("os.arch"));
        Log.i("当前用户："+System.getProperties().getProperty("user.name"));
        Log.i("公网IP "+ IPUtil.INTERNET_IP +" 内网IP "+ IPUtil.INTRANET_IP );
        Log.i("Current INTERNET_IP is "+ IPUtil.INTERNET_IP +" and INTRANET_IP "+ IPUtil.INTRANET_IP );
        if (System.getProperties().getProperty("os.name").contains("Windows")){
            Log.i(IPUtil.getIPs().toString());
        }
        OkServerOptions.setIsDebug(false);
        OkSocketOptions.setIsDebug(false);
        SLog.setIsDebug(false);

        ServerReceiver serverReceiver = new ServerReceiver(SOCKET_PORT);
        IServerManager serverManager = OkSocket.server(SOCKET_PORT).registerReceiver(serverReceiver);
        serverReceiver.setServerManager(serverManager);
        serverManager.listen();
    }

}