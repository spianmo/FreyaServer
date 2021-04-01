package com.kirshi.freya.server.exception;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:MissSuperkeyException.java
 * @LastModified:2021-04-01T20:03:11.060+08:00
 */

public class MissSuperkeyException extends RuntimeException {
    public MissSuperkeyException(String message) {
        super(message);
    }
}
