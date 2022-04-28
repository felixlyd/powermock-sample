package com.example.powermockdemo.service.impl;

import com.example.powermockdemo.dao.dos.impl.UserDaoArgumentMatch;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/4/28
 */
public class UserServiceArgumentMatchImpl {

    private UserDaoArgumentMatch userDaoArgumentMatch;

    public String queryUserPhoneNumber(String userName){
        return userDaoArgumentMatch.getPhoneNumber(userName);
    }
}
