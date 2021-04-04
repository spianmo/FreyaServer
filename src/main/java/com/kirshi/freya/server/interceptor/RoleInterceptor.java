package com.kirshi.freya.server.interceptor;

import com.kirshi.freya.server.annotation.RequireVidCreater;
import com.kirshi.freya.server.annotation.RequireVidVisitor;
import com.kirshi.freya.server.bean.Assist;
import com.kirshi.freya.server.exception.InsufficAuthException;
import com.kirshi.freya.server.exception.MissingParamException;
import com.kirshi.freya.server.service.AssistService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:RoleInterceptor.java
 * @LastModified:2021-04-05T01:21:15.102+08:00
 */

@Slf4j
public class RoleInterceptor implements HandlerInterceptor {

    @Autowired
    private AssistService mAssistService;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod method = (HandlerMethod) handler;
        RequireVidCreater vidCreaterRequired = method.getMethodAnnotation(RequireVidCreater.class);
        RequireVidVisitor vidVisitorRequired = method.getMethodAnnotation(RequireVidVisitor.class);
        String uid = request.getHeader("uid");
        String vid = request.getParameter("vid");

        if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(vid)) {
            throw new MissingParamException("缺失重要参数，非法请求！");
        }
        Assist assist = mAssistService.query(vid);

        if (vidCreaterRequired != null) {
            if (assist != null && assist.getUid().equals(uid)) {
                return true;
            } else {
                throw new InsufficAuthException("权限不足，资源访问受限");
            }
        } else if (vidVisitorRequired != null) {
            if (assist != null && assist.getPeerUid().equals(uid)) {
                return true;
            } else {
                throw new InsufficAuthException("权限不足，资源访问受限");
            }
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, @Nullable ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, @Nullable Exception ex) {
    }

}
