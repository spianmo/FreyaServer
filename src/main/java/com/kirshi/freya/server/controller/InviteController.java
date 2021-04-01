package com.kirshi.freya.server.controller;

import com.kirshi.freya.server.base.BaseResponse;
import com.kirshi.freya.server.base.CodeConstant;
import com.kirshi.freya.server.bean.Invite;
import com.kirshi.freya.server.service.InviteService;
import com.kirshi.freya.server.utils.RandomUtil;
import com.xuhao.didi.socket.common.interfaces.utils.TextUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:InviteController.java
 * @LastModified:2021-04-01T20:03:11.030+08:00
 */

@RestController
@RequestMapping("/invite")
public class InviteController {
    @Resource
    private InviteService mInviteService;

    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    public BaseResponse<Invite> add(@Valid @RequestBody Invite invite) {
        invite.setVid(RandomUtil.createUid(10));
        if (TextUtils.isEmpty(invite.getSecret())) {
            invite.setSecret(RandomUtil.createUid(6));
        }
        invite.setUsed(false);
        if (mInviteService.insert(invite)) {
            return new BaseResponse<>(CodeConstant.Success, "创建远程协助邀请成功", mInviteService.query(invite));
        } else {
            return new BaseResponse<>(CodeConstant.Faild, "创建远程协助邀请失败");
        }
    }
}
