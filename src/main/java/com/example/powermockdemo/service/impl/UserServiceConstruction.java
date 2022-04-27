package com.example.powermockdemo.service.impl;

import com.example.powermockdemo.dao.dos.impl.UserDaoConstruction;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/4/26
 */
public class UserServiceConstruction {
    public void save(String username, String password){
        UserDaoConstruction userDaoConstruction = new UserDaoConstruction(username, password);
        userDaoConstruction.insert();
    }
}
