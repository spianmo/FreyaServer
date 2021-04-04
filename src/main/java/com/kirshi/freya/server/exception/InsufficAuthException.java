package com.kirshi.freya.server.exception;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:InsufficAuthException.java
 * @LastModified:2021-04-05T01:21:15.094+08:00
 */

public class InsufficAuthException extends RuntimeException {
    public InsufficAuthException(String error) {
        super(error);
    }
}
