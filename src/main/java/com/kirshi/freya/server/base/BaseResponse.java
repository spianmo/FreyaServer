package com.kirshi.freya.server.base;

import lombok.Data;

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
