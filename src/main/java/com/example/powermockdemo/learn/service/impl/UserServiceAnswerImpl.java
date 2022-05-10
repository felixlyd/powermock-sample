package com.example.powermockdemo.learn.service.impl;

import com.example.powermockdemo.learn.dao.dos.impl.UserDaoArgumentMatch;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/4/28
 */
public class UserServiceAnswerImpl {

    private UserDaoArgumentMatch userDaoArgumentMatch;

    public String queryUserPhoneNumber(String userName){
        return userDaoArgumentMatch.getPhoneNumber(userName);
    }
}
