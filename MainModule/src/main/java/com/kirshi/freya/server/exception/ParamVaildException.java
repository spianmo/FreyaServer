package com.kirshi.freya.server.exception;

/**
 * Copyright (c) 2022
 *
 * @Project: CollectionBackend
 * @Author: Finger
 * @FileName: ParamVaildException.java
 * @LastModified: 2022/01/17 13:30:17
 */

public class ParamVaildException extends RuntimeException {
    public ParamVaildException(String message) {
        super(message);
    }
}
