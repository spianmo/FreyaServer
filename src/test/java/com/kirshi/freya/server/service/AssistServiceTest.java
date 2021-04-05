package com.kirshi.freya.server.service;

import com.kirshi.freya.server.FreyaServerApplication;
import com.kirshi.freya.server.dao.AssistDao;
import com.kirshi.freya.server.dto.AssistVisitorDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:AssistServiceTest.java
 * @LastModified:2021-04-06T00:53:35.762+08:00
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FreyaServerApplication.class)
class AssistServiceTest {
    @Autowired
    AssistDao mAssistDao;

    @Test
    void queryAssistDto() {
        List<AssistVisitorDto> list = mAssistDao.queryAssistDto("0");
        Assertions.assertEquals(0, list.size());
    }
}