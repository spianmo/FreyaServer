package com.kirshi.freya.server.interceptor;

import com.kirshi.freya.server.annotation.LoginStatus;
import com.kirshi.freya.server.exception.AbnormalLoginException;
import com.kirshi.freya.server.exception.MissSuperkeyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
 * @LastModified:2021-04-01T20:03:11.065+08:00
 */

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle 运行了这里");
        //判断如果不是请求control方法直接返回true
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod method = (HandlerMethod) handler;
        //判断访问的control是否添加LoginRequired注解
        LoginStatus loginRequired = method.getMethodAnnotation(LoginStatus.class);
        boolean required = true;
        if (loginRequired != null) {
            required = loginRequired.required();
            //如果添加了requird注解，则在消息请求头中查看token是否存在
            String superkey = request.getHeader("superkey");
            if (StringUtils.isEmpty(superkey)) {
                throw new MissSuperkeyException("登录态异常，资源访问受限");
            }
            log.debug("superkey----------------:" + superkey);
            // 从 http 请求头中取出 token,获取token的内容并解析，然后判断用户是否存在、是否登录、是否在线超时等
            if ("superkey".equals(superkey)) {
                return true;
            } else {
                throw new AbnormalLoginException("登录态异常，资源访问受限");
            }
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {
        log.debug("postHandle()");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
        log.debug("afterCompletion()");
    }

}
