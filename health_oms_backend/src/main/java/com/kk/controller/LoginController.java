package com.kk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kk.common.MessageConst;
import com.kk.entity.Result;
import com.kk.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginController {
    @Reference
    private UserService userService;
    @RequestMapping("/login")
    public Result login(String name, String pwd){
        Boolean aBoolean = userService.toLogin(name, pwd);
        if(aBoolean){
            return new Result(true,MessageConst.ACTION_SUCCESS,name);
        }
        return new Result(false,MessageConst.ACTION_FAIL);
    }
}
