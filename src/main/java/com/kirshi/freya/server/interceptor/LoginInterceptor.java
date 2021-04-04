package com.kirshi.freya.server.interceptor;

import com.kirshi.freya.server.annotation.RequireLogin;
import com.kirshi.freya.server.bean.Session;
import com.kirshi.freya.server.exception.AbnormalLoginException;
import com.kirshi.freya.server.exception.MissSuperkeyException;
import com.kirshi.freya.server.service.SessionService;
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
 * @FileName:LoginInterceptor.java
 * @LastModified:2021-04-05T01:21:15.099+08:00
 */

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionService mSessionService;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        //判断如果不是请求control方法直接返回true
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod method = (HandlerMethod) handler;
        //判断访问的control是否添加LoginRequired注解
        RequireLogin loginRequired = method.getMethodAnnotation(RequireLogin.class);
        if (loginRequired != null) {
            String uid = request.getHeader("uid");
            String superkey = request.getHeader("superkey");
            if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(superkey)) {
                throw new MissSuperkeyException("登录态异常，资源访问受限");
            }
            Session session = mSessionService.getSuperkey(uid);
            if (session != null && session.getSuperkey().equals(superkey)) {
                return true;
            } else {
                throw new AbnormalLoginException("登录态异常，资源访问受限");
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
