package com.kk.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.kk.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public Boolean toLogin(String name, String pwd) {
        if("root".equals(name) && "123".equals(pwd)){
            return true;
        }
        return false;
    }
}
