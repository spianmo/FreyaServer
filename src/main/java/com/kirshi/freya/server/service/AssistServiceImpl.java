package com.kirshi.freya.server.service;

import com.kirshi.freya.server.bean.Assist;
import com.kirshi.freya.server.bean.Device;
import com.kirshi.freya.server.dao.AssistDao;
import com.kirshi.freya.server.dto.AssistCreaterDto;
import com.kirshi.freya.server.dto.AssistVisitorDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:AssistServiceImpl.java
 * @LastModified:2021-04-06T00:53:35.759+08:00
 */
@Service
public class AssistServiceImpl implements AssistService {
    @Resource
    AssistDao mAssistDao;

    @Override
    public boolean insert(Assist assist) {
        if (isWork(assist.getVid())) {
            return true;
        } else {
            return mAssistDao.insert(assist);
        }
    }

    @Override
    public boolean isWork(String vid) {
        List<Assist> list = mAssistDao.query(vid);
        if (list.isEmpty()) {
            return false;
        }
        return list.get(0).getStatus() == Assist.Status.Unused;
    }

    @Override
    public List<Device.Permission> queryVidPermissions(String vid) {
        return mAssistDao.queryVidPermissions(vid);
    }

    @Override
    public Assist query(String vid) {
        return mAssistDao.query(vid).get(0);
    }

    @Override
    public boolean settingAssist(String vid, Assist assist) {
        return mAssistDao.update(vid, assist);
    }

    @Override
    public boolean updateAssist(String vid, String alias) {
        return mAssistDao.updateAlias(vid, alias);
    }

    @Override
    public boolean updateStatus(String vid, Assist.Status status) {
        return mAssistDao.updateStatus(vid, status);
    }

    @Override
    public boolean updatePeerUid(String vid, String peerUid) {
        return mAssistDao.updatePeerUid(vid, peerUid);
    }

    @Override
    public List<AssistVisitorDto> queryAssistDto(String uid) {
        return mAssistDao.queryAssistDto(uid);
    }

    @Override
    public List<AssistCreaterDto> queryAssistedDto(String uid) {
        return mAssistDao.queryAssistedDto(uid);
    }
}
