package com.kirshi.freya.server.service.impl;

import cn.hutool.core.util.StrUtil;
import com.kirshi.freya.server.config.RedisUtil;
import com.kirshi.freya.server.service.RemoteService;
import com.kirshi.freya.server.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class RemoteServiceImpl implements RemoteService {

    @Autowired
    RedisUtil redis;
    @Override
    public String createValidKey(int length) {
        String sessionKey = RandomUtil.createUid(length);
        while (redis.sHasKey("SessionKeys", sessionKey)) {
            sessionKey = RandomUtil.createUid(length);
        }
        redis.sSet("SessionKeys", sessionKey);
        return sessionKey;
    }
}
