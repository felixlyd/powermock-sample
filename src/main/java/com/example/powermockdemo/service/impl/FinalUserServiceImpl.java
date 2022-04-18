package com.example.powermockdemo.service.impl;

import com.example.powermockdemo.dao.dos.UserDataService;
import com.example.powermockdemo.dao.vdo.UserDO;
import com.example.powermockdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/4/18
 */
public class FinalUserServiceImpl implements UserService {

    private final UserDataService userDataService;

    public FinalUserServiceImpl(@Qualifier("finalUserDataServiceImpl") UserDataService userDataService) {
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
