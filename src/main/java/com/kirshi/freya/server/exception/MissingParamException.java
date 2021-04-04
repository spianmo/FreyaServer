package com.kirshi.freya.server.exception;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:MissingParamException.java
 * @LastModified:2021-04-05T01:21:15.096+08:00
 */

public class MissingParamException extends RuntimeException {
    public MissingParamException(String error) {
        super(error);
    }
}
