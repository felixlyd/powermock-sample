package com.example.powermockdemo.note.service.user.impl;

import com.example.powermockdemo.learn.dao.vdo.UserDO;
import com.example.powermockdemo.note.dao.UserDao;
import com.example.powermockdemo.note.dao.impl.UserDaoImpl;
import com.example.powermockdemo.note.service.user.UserService;
import org.springframework.stereotype.Service;

/**
 * user对象相关的逻辑服务实现类
 * 在每个方法中通过new对象的方式加载UserDao
 *
 * @author : liuyaodong
 * @date 2022/5/12
 */
@Service
public class UserServiceWithLocalVarImpl implements UserService {
    @Override
    public int queryUserCount() {
        UserDao userDao = new UserDaoImpl();
        return userDao.getUserCount();
    }

    @Override
    public void saveUser(UserDO userDO) {
        UserDao userDao = new UserDaoImpl();
        userDao.insertUser(userDO);
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
