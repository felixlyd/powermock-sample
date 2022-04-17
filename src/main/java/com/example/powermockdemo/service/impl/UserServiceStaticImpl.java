package com.example.powermockdemo.service.impl;

import com.example.powermockdemo.common.CommonService;
import com.example.powermockdemo.dao.vdo.UserDO;
import com.example.powermockdemo.service.UserService;

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
