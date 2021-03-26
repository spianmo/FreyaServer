package com.kirshi.freya.server.socket.utils;

import com.tracker.server.TrackerServerApplication;

/**
 * Copyright (c) 2021  Spianmo, Inc. All rights reserved.
 * @Project TrackerServer
 * @Author Finger
 * @FileName Log.java
 * @LastModified 2021-03-04 22:56:49
 */

public class Log {

    public static void i(String log) {
        TrackerServerApplication.logger.info(log);
    }

    public static void e(String log) {
        TrackerServerApplication.logger.error(log);
    }

    public static void d(String log) {
        TrackerServerApplication.logger.debug(log);
    }

    public static void w(String log) {
        TrackerServerApplication.logger.warn(log);
    }

}
