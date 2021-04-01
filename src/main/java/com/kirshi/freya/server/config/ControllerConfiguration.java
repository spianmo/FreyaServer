package com.kirshi.freya.server.config;

import com.kirshi.freya.server.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:ControllerConfiguration.java
 * @LastModified:2021-04-01T20:03:11.016+08:00
 */


@Configuration
public class ControllerConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public LoginInterceptor demoInterceptor() {
        return new LoginInterceptor();
    }
}
