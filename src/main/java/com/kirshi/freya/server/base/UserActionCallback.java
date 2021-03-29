package com.kirshi.freya.server.base;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:UserActionCallback.java
 * @LastModified:2021-03-29T17:17:13.226+08:00
 */

public class UserActionCallback<T> extends BaseResponse<T> {
    public UserActionCallback(int status, T data) {
        super(status, data);
    }

    public UserActionCallback(int status, String message, T data) {
        super(status, message, data);
    }

    public UserActionCallback(int status, String message) {
        super(status, message);
    }
}
