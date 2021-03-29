package com.hidata.framework.annotation.db;

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
 * @FileName:DBExclude.java
 * @LastModified:2021-03-29T17:16:09.313+08:00
 */

/**
 * 在 Model类的属性前标注{@link DBExclude} 表示该该属性不被映射到数据库中
 *
 * @author Li Hongxu
 */
@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DBExclude {
    public boolean value() default true;
}
