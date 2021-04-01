package com.kirshi.freya.server.exception;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:AbnormalLoginException.java
 * @LastModified:2021-04-01T20:03:11.056+08:00
 */

public class AbnormalLoginException extends RuntimeException {
    public AbnormalLoginException(String error) {
        super(error);
    }
}
