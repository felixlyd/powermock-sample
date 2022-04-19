package com.example.powermockdemo.service.impl;

import com.example.powermockdemo.dao.dos.impl.TestVerifyUserDao;
import com.example.powermockdemo.dao.vdo.UserDO;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/4/19
 */
public class TestVerifyUserService {

    public void saveOrUpdate(UserDO userDO){
        TestVerifyUserDao testVerifyUserDao = new TestVerifyUserDao();
        if(testVerifyUserDao.getUserCount()>0){
            testVerifyUserDao.updateUser(userDO);
        }else {
            testVerifyUserDao.saveUser(userDO);
        }
    }
}
