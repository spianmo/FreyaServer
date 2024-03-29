package com.kirshi.freya.server.controller;

import com.kirshi.freya.server.base.BaseResponse;
import com.kirshi.freya.server.base.HttpStatusMsg;
import com.kirshi.freya.server.exception.AbnormalLoginException;
import com.kirshi.freya.server.exception.InsufficAuthException;
import com.kirshi.freya.server.exception.MissSuperkeyException;
import com.kirshi.freya.server.exception.MissingParamException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:ExceptionAdviceHandler.java
 * @LastModified:2021-04-05T01:21:15.084+08:00
 */

/**
 * @author Finger
 * @Description: 控制并统一处理异常类
 * @ExceptionHandler标注的方法优先级问题，它会找到异常的最近继承关系，也就是继承关系最浅的注解方法
 * @Version: 1.0
 */
@RestControllerAdvice
public final class ExceptionAdviceHandler {

    /**
     * 未知异常
     */
    @ExceptionHandler(value = Exception.class)
    public BaseResponse<String> unKnowExceptionHandler(Exception e) {
        e.printStackTrace();
        StackTraceElement[] elements = e.getStackTrace();
        String message = StringUtils.EMPTY;
        if(elements.length > 0){
            StackTraceElement element = elements[0];
            message = StringUtils.join("控制器", element.getClassName(), ".", element.getMethodName(), "类的第", element.getLineNumber(), "行发生", e.toString(), "异常");
        }
        if(StringUtils.isBlank(message)){
            message = e.toString();
        }
        return new BaseResponse<>(HttpStatusMsg.UNKNOW_EXCEPTION.getStatus(), message);
    }

    /**
     * 运行时异常
     */
    @ExceptionHandler(value = RuntimeException.class)
    public BaseResponse<String> runtimeExceptionHandler(RuntimeException e) {
        e.printStackTrace();
        StackTraceElement[] elements = e.getStackTrace();
        String message = StringUtils.EMPTY;
        if(elements.length > 0){
            StackTraceElement element = elements[0];
            message = StringUtils.join("控制器", element.getClassName(), ".", element.getMethodName(), "类的第", element.getLineNumber(), "行发生", e.toString(), "异常");
        }
        if(StringUtils.isBlank(message)){
            message = e.toString();
        }
        return new BaseResponse<>(HttpStatusMsg.RUNTIME_EXCEPTION.getStatus(), message);
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public BaseResponse<String> nullPointerExceptionHandler(NullPointerException e) {
        e.printStackTrace();
        StackTraceElement[] elements = e.getStackTrace();
        String message = StringUtils.EMPTY;
        if (elements.length > 0) {
            StackTraceElement element = elements[0];
            message = StringUtils.join("控制器", element.getClassName(), ".", element.getMethodName(), "类的第", element.getLineNumber(), "行发生", e.toString(), "异常");
        }
        if (StringUtils.isBlank(message)) {
            message = e.toString();
        }
        return new BaseResponse<>(HttpStatusMsg.NULL_POINTER_EXCEPTION.getStatus(), message);
    }

    @ExceptionHandler({AbnormalLoginException.class})
    public BaseResponse<String> abnormalLoginException(AbnormalLoginException e) {
        e.printStackTrace();
        String message = e.getMessage();
        return new BaseResponse<>(HttpStatusMsg.AUTHENTICATION_EXCEPTION.getStatus(), message);
    }

    @ExceptionHandler({MissSuperkeyException.class})
    public BaseResponse<String> missSuperkeyException(MissSuperkeyException e) {
        e.printStackTrace();
        String message = e.getMessage();
        return new BaseResponse<>(HttpStatusMsg.AUTHENTICATION_EXCEPTION.getStatus(), message);
    }

    @ExceptionHandler({MissingParamException.class})
    public BaseResponse<String> missingParamException(MissingParamException e) {
        e.printStackTrace();
        return new BaseResponse<>(HttpStatusMsg.MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION.getStatus(), e.getMessage());
    }

    @ExceptionHandler({InsufficAuthException.class})
    public BaseResponse<String> insufficAuthException(InsufficAuthException e) {
        e.printStackTrace();
        return new BaseResponse<>(HttpStatusMsg.AUTHENTICATION_EXCEPTION.getStatus(), e.getMessage());
    }

    /**
     * 类型转换异常
     */
    @ExceptionHandler(ClassCastException.class)
    public BaseResponse<String> classCastExceptionHandler(ClassCastException e) {
        e.printStackTrace();
        StackTraceElement[] elements = e.getStackTrace();
        String message = StringUtils.EMPTY;
        if (elements.length > 0) {
            StackTraceElement element = elements[0];
            message = StringUtils.join("控制器", element.getClassName(), ".", element.getMethodName(), "类的第", element.getLineNumber(), "行发生", e.toString(), "异常");
        }
        if(StringUtils.isBlank(message)){
            message = e.toString();
        }
        return new BaseResponse<>(HttpStatusMsg.CLASS_CAST_EXCEPTION.getStatus(), message);
    }

    /**
     * IO异常
     */
    @ExceptionHandler(IOException.class)
    public BaseResponse<String> iOExceptionHandler(IOException e) {
        e.printStackTrace();
        StackTraceElement[] elements = e.getStackTrace();
        String message = StringUtils.EMPTY;
        if(elements.length > 0){
            StackTraceElement element = elements[0];
            message = StringUtils.join("控制器", element.getClassName(), ".", element.getMethodName(), "类的第", element.getLineNumber(), "行发生", e.toString(), "异常");
        }
        if(StringUtils.isBlank(message)){
            message = e.toString();
        }
        return new BaseResponse<>(HttpStatusMsg.IO_EXCEPTION.getStatus(), message);
    }

    /**
     * 数组越界异常
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<String> indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException e) {
        e.printStackTrace();
        StackTraceElement[] elements = e.getStackTrace();
        String message = StringUtils.EMPTY;
        if(elements.length > 0){
            StackTraceElement element = elements[0];
            message = StringUtils.join("控制器", element.getClassName(), ".", element.getMethodName(), "类的第", element.getLineNumber(), "行发生", e.toString(), "异常");
        }
        if(StringUtils.isBlank(message)){
            message = e.toString();
        }
        return new BaseResponse<>(HttpStatusMsg.INDEX_OUTOF_BOUNDS_EXCEPTION.getStatus(), message);
    }

    /**
     * 参数类型不匹配
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public BaseResponse<String> requestTypeMismatch(MethodArgumentTypeMismatchException e){
        return new BaseResponse<>(HttpStatusMsg.METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTIION.getStatus(), "参数类型不匹配，参数"+e.getName()+""+e.getRequiredType());
    }
    /**
     * 缺少参数
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public BaseResponse<String> requestMissingServletRequest(MissingServletRequestParameterException e) {
        return new BaseResponse<>(HttpStatusMsg.MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION.getStatus(), "缺少必要参数，参数名称为"+e.getParameterName());
    }
    /**
     * 请求method不匹配
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public BaseResponse<String> requestMissingServletRequest(HttpRequestMethodNotSupportedException e) {
        return new BaseResponse<>(HttpStatusMsg.HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION.getStatus(), "不支持"+e.getMethod()+"方法，支持"+ StringUtils.join(e.getSupportedMethods(), ",")+"类型");
    }

    /**
     *
     * 控制器方法中@RequestBody类型参数数据类型转换异常
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public BaseResponse<String> httpMessageNotReadableException(HttpMessageNotReadableException e, WebRequest wq){
        e.printStackTrace();
        return new BaseResponse<>(HttpStatusMsg.PARAM_EXCEPTION.getStatus(), "请求参数异常");
    }

    /**
     *
     * 控制器方法参数异常
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public BaseResponse<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        e.printStackTrace();
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String message = StringUtils.join(fieldError.getDefaultMessage());
        return new BaseResponse<>(HttpStatusMsg.PARAM_EXCEPTION.getStatus(), message);
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    public BaseResponse<String> noHandlerFoundException(NoHandlerFoundException e) {
        e.printStackTrace();
        return new BaseResponse<>(HttpStatusMsg.NOT_FOUND_EXCEPTION.getStatus(), e.getMessage());
    }

}
