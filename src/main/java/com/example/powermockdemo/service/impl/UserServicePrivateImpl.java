package com.example.powermockdemo.service.impl;

import com.example.powermockdemo.dao.dos.impl.UserDaoArgumentMatch;

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
