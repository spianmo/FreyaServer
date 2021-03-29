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
 * @FileName:Column.java
 * @LastModified:2021-03-29T17:16:09.309+08:00
 */

/**
 * 定义数据库字段名
 *
 * @author houzhaowei
 */
@Inherited
@Target({ElementType.FIELD})//在属性上起作用
@Retention(RetentionPolicy.RUNTIME) //运行时起作用
@Documented
public @interface Column {
	/**
	 * 字段名
	 *
	 * @return
	 */
	String value() default "";
}
