package com.kirshi.freya.server.controller;

import com.kirshi.freya.server.annotation.RequireLogin;
import com.kirshi.freya.server.annotation.RequireVidCreater;
import com.kirshi.freya.server.annotation.RequireVidVisitor;
import com.kirshi.freya.server.base.BaseResponse;
import com.kirshi.freya.server.base.CodeConstant;
import com.kirshi.freya.server.bean.Assist;
import com.kirshi.freya.server.bean.Device;
import com.kirshi.freya.server.dto.AssistCreaterDto;
import com.kirshi.freya.server.dto.AssistVisitorDto;
import com.kirshi.freya.server.service.AssistService;
import com.kirshi.freya.server.utils.RandomUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;


/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:AssistController.java
 * @LastModified:2021-04-11T23:37:28.303+08:00
 */

@RestController
@RequestMapping("/assist")
public class AssistController {
    @Resource
    private AssistService mAssistService;


    @GetMapping("/invite")
    public ModelAndView index() {
        return new ModelAndView("quickAssist");
    }

    @RequireLogin
    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    public BaseResponse<Assist> add(@Valid @RequestBody Assist assist) {
        assist.setStatus(Assist.Status.Unused);
        assist.setCreateTime(new Timestamp(System.currentTimeMillis()));
        if (mAssistService.insert(assist)) {
            return new BaseResponse<>(CodeConstant.Success, "创建远程协助邀请成功", mAssistService.query(assist.getVid()));
        } else {
            return new BaseResponse<>(CodeConstant.Faild, "创建远程协助邀请失败");
        }
    }

    @RequireLogin
    @GetMapping(value = "/generateVid")
    public BaseResponse<Object> generateVid() {
        String vid = RandomUtil.createUid(10);
        while (mAssistService.query(vid) == null) {
            return new BaseResponse<>(CodeConstant.Success, (Object) vid);
        }
        return new BaseResponse<>(CodeConstant.Faild, "愚蠢的JAVA语法分析器");
    }

    @RequireLogin
    @PostMapping(value = "/queryPermissions")
    public BaseResponse<List<Device.Permission>> queryPermissions(@RequestParam(name = "vid") String vid) {
        List<Device.Permission> permissions = mAssistService.queryVidPermissions(vid);
        return permissions.isEmpty() ? new BaseResponse<>(CodeConstant.Faild, "获取设备权限失败") : new BaseResponse<>(CodeConstant.Success, permissions);
    }

    @RequireLogin
    @RequireVidCreater
    @PostMapping(value = "/settingAssist")
    public BaseResponse<Assist> settingAssist(@RequestParam(name = "vid") String vid, @RequestBody Assist assist) {
        return mAssistService.settingAssist(vid, assist) ? new BaseResponse<>(CodeConstant.Faild, "设置协助会话失败") : new BaseResponse<>(CodeConstant.Success, mAssistService.query(vid));
    }

    @RequireLogin
    @RequireVidVisitor
    @PostMapping(value = "/updateAssist")
    public BaseResponse<Assist> updateAssist(@RequestParam(name = "vid") String vid, @RequestParam(name = "alias") String alias) {
        return mAssistService.updateAssist(vid, alias) ? new BaseResponse<>(CodeConstant.Faild, "设置协助设备名称失败") : new BaseResponse<>(CodeConstant.Success, mAssistService.query(vid));
    }

    @RequireLogin
    @PostMapping(value = "/updateStatus")
    public BaseResponse<Assist> updateStatus(@RequestParam(name = "vid") String vid, @RequestParam(name = "status") Assist.Status status) {
        return mAssistService.updateStatus(vid, status) ? new BaseResponse<>(CodeConstant.Success, mAssistService.query(vid)) : new BaseResponse<>(CodeConstant.Faild, "设备状态暂时无法改变");
    }

    @RequireLogin
    @PostMapping(value = "/peer")
    public BaseResponse<Assist> updatePeerUid(@RequestParam(name = "vid") String vid, @RequestParam(name = "secret") String secret, @RequestHeader("uid") String peerUid) {
        Assist assist = mAssistService.query(vid);
        if (assist == null) {
            return new BaseResponse<>(CodeConstant.Faild, "ID为" + vid + "的会话并不存在");
        }
        if (assist.getSecret().equals(secret)) {
            if (assist.getStatus() == Assist.Status.Unused) {
                if (assist.getUid().equals(peerUid)) {
                    return new BaseResponse<>(CodeConstant.Faild, "请不要尝试连接自己");
                }
                mAssistService.updateStatus(vid, Assist.Status.Used);
                return mAssistService.updatePeerUid(vid, peerUid) ? new BaseResponse<>(CodeConstant.Success, mAssistService.query(vid)) : new BaseResponse<>(CodeConstant.Faild, "设置协助会话对端Uid失败");
            } else {
                return new BaseResponse<>(CodeConstant.Faild, "该协助会话已被其他账号占用");
            }
        } else {
            return new BaseResponse<>(CodeConstant.Faild, "会话密码错误");
        }
    }

    @RequireLogin
    @PostMapping(value = "/queryAllAssist")
    public BaseResponse<List<AssistVisitorDto>> queryAllAssist(@RequestParam(name = "uid") String uid) {
        return new BaseResponse<>(CodeConstant.Success, mAssistService.queryAssistDto(uid));
    }

    @RequireLogin
    @PostMapping(value = "/queryAllAssisted")
    public BaseResponse<List<AssistCreaterDto>> queryAllAssisted(@RequestParam(name = "uid") String uid) {
        return new BaseResponse<>(CodeConstant.Success, mAssistService.queryAssistedDto(uid));
    }
}
