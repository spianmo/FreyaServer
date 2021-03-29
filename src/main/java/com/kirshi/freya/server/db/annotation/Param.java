package com.kirshi.freya.server.db.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:Param.java
 * @LastModified:2021-03-29T17:16:09.317+08:00
 */

@Inherited
@Target({ElementType.FIELD})//在属性上起作用
@Retention(RetentionPolicy.RUNTIME) //运行时起作用
@Documented
public @interface Param {
    /**
     * 参数名
     *
     * @return
     */
    String value() default "";
}