package com.example.powermockdemo.learn.service.impl;

import com.example.powermockdemo.learn.dao.dos.UserDataService;
import com.example.powermockdemo.learn.dao.dos.impl.UserDataServiceImpl;
import com.example.powermockdemo.learn.dao.vdo.UserDO;
import com.example.powermockdemo.learn.service.UserService;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/3/14
 */
public class UserServiceLocalImpl implements UserService {

    @Override
    public int queryUserCount() {
        UserDataService userDataService = new UserDataServiceImpl();
        return userDataService.getUserCount();
    }

    @Override
    public void saveUser(UserDO userDO) {
        UserDataService userDataService = new UserDataServiceImpl();
        userDataService.insertUser(userDO);
    }
}
