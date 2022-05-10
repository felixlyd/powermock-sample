package com.example.powermockdemo.learn.service.impl;

import com.example.powermockdemo.learn.common.CommonService;
import com.example.powermockdemo.learn.dao.vdo.UserDO;
import com.example.powermockdemo.learn.service.UserService;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/3/14
 */
public class UserServiceStaticImpl implements UserService {
    @Override
    public int queryUserCount() {
        return CommonService.getNumbers();
    }

    @Override
    public void saveUser(UserDO userDO) {
        CommonService.doSomething(userDO);
    }
}
