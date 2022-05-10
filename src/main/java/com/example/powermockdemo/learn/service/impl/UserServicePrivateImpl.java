package com.example.powermockdemo.learn.service.impl;

import com.example.powermockdemo.learn.dao.dos.impl.UserDaoArgumentMatch;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/4/28
 */
public class UserServicePrivateImpl {

    private UserDaoArgumentMatch userDaoArgumentMatch;

    public String queryUserPhoneNumber(String userName){
        if(isOk()){
            return userDaoArgumentMatch.getPhoneNumber(userName);
        }else {
            return "";
        }
    }

    private boolean isOk(){
        return false;
    }
}
