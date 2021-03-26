package com.kirshi.freya.server.controller;

import com.kirshi.freya.server.base.BaseResponse;
import com.kirshi.freya.server.base.CodeConstant;
import com.kirshi.freya.server.bean.User;
import com.kirshi.freya.server.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.UnexpectedTypeException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2021
 * @Project:FreyaServer
 * @Author:Finger
 * @FileName:UserController.java
 * @LastModified:2021-03-27T01:09:45.753+08:00
 */

/**
 * @ClassName ClientController
 * @Description TODO
 * @Author Finger
 * @Date 12/28/2020
 **/

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private AccountService mAccountService;

    @PostMapping(value = "/login")
    public BaseResponse<User> login(@RequestParam(name = "version") String version, @RequestParam(name = "openid", required = false) String openid, @RequestParam(name = "account", required = false) String account, @RequestParam(name = "passwd", required = false) String passwd) {
        User user = new User();
        user.setOpenid(openid);
        user.setAccount(account);
        user.setPasswd(passwd);
        List<User> users = mAccountService.queryExistUser(version,user);
        if (users.size() == 0) {
            return new BaseResponse<>(CodeConstant.UserNoExist, "用户不存在");
        } else {
            if ("social".equals(version)) {
                return new BaseResponse<>(CodeConstant.Success, users.get(0));
            } else if ("common".equals(version)) {
                if (mAccountService.checkPasswd(user)){
                    return new BaseResponse<>(CodeConstant.Success,"登陆成功",users.get(0));
                }else {
                    return new BaseResponse<>(CodeConstant.Faild, "账号或密码错误");
                }
            }
        }
        return new BaseResponse<>(CodeConstant.ArgumentNotValid, "参数错误");
    }
}
