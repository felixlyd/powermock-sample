package com.example.powermocklearndemo.service.impl;

import com.example.powermocklearndemo.dao.dos.UserDataService;
import com.example.powermocklearndemo.dao.vdo.UserDO;
import com.example.powermocklearndemo.service.UserService;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/3/7
 */
public class UserServiceImpl implements UserService {

    private final UserDataService userDataService;

    public UserServiceImpl(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @Override
    public int queryUserCount() {
        return userDataService.getUserCount();
    }

    @Override
    public void saveUser(UserDO userDO) {
        userDataService.insertUser(userDO);
    }
}
