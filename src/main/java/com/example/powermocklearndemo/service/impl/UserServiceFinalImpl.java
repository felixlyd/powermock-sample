package com.example.powermocklearndemo.service.impl;

import com.example.powermocklearndemo.dao.dos.UserDataService;
import com.example.powermocklearndemo.dao.vdo.UserDO;
import com.example.powermocklearndemo.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/4/18
 */
public class UserServiceFinalImpl implements UserService {

    private final UserDataService userDataService;

    public UserServiceFinalImpl(@Qualifier("finalUserDataServiceImpl") UserDataService userDataService) {
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
