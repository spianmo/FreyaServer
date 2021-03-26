package com.kirshi.freya.server.base;

import lombok.Data;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:BaseResponse.java
 * @LastModified:2021-03-27T01:09:45.725+08:00
 */

/**
 * @ClassName BaseResponseEntity
 * @Description TODO
 * @Author Finger
 * @Date 1/4/2021
 **/
@Data
public class BaseResponse<T> {
    private T data;
    private String msg;
    private int statusCode;


    public BaseResponse(int statusCode, String msg) {
        this.msg = msg;
        this.statusCode = statusCode;
    }

    public BaseResponse(int statusCode, T data) {
        this.data = data;
        this.statusCode = statusCode;
    }

    public BaseResponse(int statusCode, String msg, T data) {
        this.data = data;
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
