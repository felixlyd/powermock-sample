package com.example.powermocklearndemo.service.impl;

import com.example.powermocklearndemo.dao.dos.impl.UserDaoArgumentMatch;

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
