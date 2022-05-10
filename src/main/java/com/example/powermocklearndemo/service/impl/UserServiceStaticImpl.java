package com.example.powermocklearndemo.service.impl;

import com.example.powermocklearndemo.common.CommonService;
import com.example.powermocklearndemo.dao.vdo.UserDO;
import com.example.powermocklearndemo.service.UserService;

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
