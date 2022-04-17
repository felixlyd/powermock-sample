package com.example.powermockdemo.service.impl;

import com.example.powermockdemo.dao.dos.UserDataService;
import com.example.powermockdemo.dao.dos.impl.UserDataServiceImpl;
import com.example.powermockdemo.dao.vdo.UserDO;
import com.example.powermockdemo.service.UserService;

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
