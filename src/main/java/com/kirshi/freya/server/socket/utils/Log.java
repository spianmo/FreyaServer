package com.kirshi.freya.server.socket.utils;


import com.kirshi.freya.server.FreyaServerApplication;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:Log.java
 * @LastModified:2021-03-27T01:09:45.795+08:00
 */

public class Log {

    public static void i(String log) {
        FreyaServerApplication.logger.info(log);
    }

    public static void e(String log) {
        FreyaServerApplication.logger.error(log);
    }

    public static void d(String log) {
        FreyaServerApplication.logger.debug(log);
    }

    public static void w(String log) {
        FreyaServerApplication.logger.warn(log);
    }

}
