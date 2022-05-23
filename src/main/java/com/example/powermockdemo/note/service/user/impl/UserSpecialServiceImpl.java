package com.example.powermockdemo.note.service.user.impl;

import com.example.powermockdemo.note.entity.UserDO;
import com.example.powermockdemo.note.service.common.SpecialService;
import com.example.powermockdemo.note.service.user.UserService;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/5/23
 */
public class UserSpecialServiceImpl implements UserService {

    @Override
    public int queryUserCount() {
        return SpecialService.getNumbers();
    }

    @Override
    public void saveUser(UserDO userDO) {

    }

    @Override
    public int queryUserCountWithPrivateMethod() {
        return 0;
    }

    @Override
    public void saveUserWithPrivateMethod(UserDO userDO) {

    }

    @Override
    public int queryUserCountWithStaticMethod() {
        return 0;
    }

    @Override
    public void saveUserWithStaticMethod(UserDO userDO) {

    }

    @Override
    public String queryUserPhoneNumber(UserDO userDO) {
        return null;
    }
}
