package com.kirshi.freya.server.controller;

import com.kirshi.freya.server.base.BaseResponse;
import com.kirshi.freya.server.base.CodeConstant;
import com.kirshi.freya.server.bean.User;
import com.kirshi.freya.server.service.AccountService;
import com.kirshi.freya.server.service.SessionService;
import com.kirshi.freya.server.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:UserController.java
 * @LastModified:2021-04-10T15:23:03.580+08:00
 */

/**
 * @ClassName ClientController
 * @Description TODO
 * @Author Finger
 * @Date 12/28/2020
 **/

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private AccountService mAccountService;
    @Resource
    private SessionService mSessionService;

    @PostMapping(value = "/login")
    public BaseResponse<User> login(@RequestParam(name = "version") String version, @RequestParam(name = "openid", required = false) String openid, @RequestParam(name = "account", required = false) String account, @RequestParam(name = "passwd", required = false) String passwd, HttpServletResponse response) {
        User user = User.builder()
                .openid(openid)
                .account(account)
                .passwd(passwd != null ? DigestUtils.md5DigestAsHex(passwd.getBytes()) : "")
                .build();
        List<User> users = mAccountService.queryExistUser(version, user);
        if (users.size() == 0) {
            return new BaseResponse<>(CodeConstant.UserNoExist, "用户不存在");
        } else {
            if ("social".equals(version)) {
                String superkey;
                if (mSessionService.getSuperkey(users.get(0).getUid()) == null) {
                    superkey = mSessionService.generateSuperkey(users.get(0).getUid());
                } else {
                    superkey = mSessionService.updateSuperkey(users.get(0).getUid());
                }
                response.addHeader("superkey", superkey);
                response.addHeader("uid", users.get(0).getUid());
                return new BaseResponse<>(CodeConstant.Success, users.get(0));
            } else if ("common".equals(version)) {
                if (mAccountService.checkPasswd(user)) {
                    String superkey;
                    if (mSessionService.getSuperkey(users.get(0).getUid()) == null) {
                        superkey = mSessionService.generateSuperkey(users.get(0).getUid());
                    } else {
                        superkey = mSessionService.updateSuperkey(users.get(0).getUid());
                    }
                    response.addHeader("superkey", superkey);
                    response.addHeader("uid", users.get(0).getUid());
                    return new BaseResponse<>(CodeConstant.Success, "登陆成功", users.get(0));
                } else {
                    return new BaseResponse<>(CodeConstant.LoginFaild, "账号或密码错误");
                }
            }
        }
        return new BaseResponse<>(CodeConstant.ArgumentNotValid, "参数错误");
    }

    @PostMapping(value = "/reg", produces = "application/json;charset=UTF-8")
    public BaseResponse<User> reg(@Valid @RequestBody User user, HttpServletResponse response) {
        user.setPasswd(DigestUtils.md5DigestAsHex(user.getPasswd().getBytes()));
        if (mAccountService.isUserExist("OPEN_ID", user.getOpenid())) {
            if (mAccountService.isUserExist("ACCOUNT", user.getAccount())) {
                user.setRegTime(new Timestamp(System.currentTimeMillis()));
                user.setUid(RandomUtil.createUid(10));
                if (mAccountService.insertUser(user)) {
                    String superkey;
                    if (mSessionService.getSuperkey(user.getUid()) == null) {
                        superkey = mSessionService.generateSuperkey(user.getUid());
                    } else {
                        superkey = mSessionService.updateSuperkey(user.getUid());
                    }
                    response.addHeader("superkey", superkey);
                    response.addHeader("uid", user.getUid());
                    return new BaseResponse<>(CodeConstant.Success, user);
                } else {
                    new BaseResponse<>(CodeConstant.UserRegError, "注册失败");
                }
            } else {
                return new BaseResponse<>(CodeConstant.UserRegedited, "用户已被注册");
            }
        } else {
            return new BaseResponse<>(CodeConstant.UserRegedited, "当前OAuth2.0鉴权账号已绑定另一Freya账号，请使用其他QQ账号绑定");
        }
        return new BaseResponse<>(CodeConstant.ArgumentNotValid, "参数错误");
    }

}
