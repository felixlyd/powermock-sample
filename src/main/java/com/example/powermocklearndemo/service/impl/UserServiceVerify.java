package com.example.powermocklearndemo.service.impl;

import com.example.powermocklearndemo.dao.dos.impl.UserDaoVerify;
import com.example.powermocklearndemo.dao.vdo.UserDO;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/4/19
 */
public class UserServiceVerify {

    public void saveOrUpdate(UserDO userDO){
        UserDaoVerify userDaoVerify = new UserDaoVerify();
        if(userDaoVerify.getUserCount()>0){
            userDaoVerify.updateUser(userDO);
        }else {
            userDaoVerify.saveUser(userDO);
        }
    }
}
