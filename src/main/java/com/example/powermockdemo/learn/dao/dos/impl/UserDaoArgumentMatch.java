package com.example.powermockdemo.learn.dao.dos.impl;

import org.springframework.stereotype.Service;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/4/28
 */
@Service
public class UserDaoArgumentMatch {

    public String getPhoneNumber(String userName){
        throw new UnsupportedOperationException();
    }
}
