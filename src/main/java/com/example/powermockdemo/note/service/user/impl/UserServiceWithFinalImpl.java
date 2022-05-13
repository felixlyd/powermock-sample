package com.example.powermockdemo.note.service.user.impl;

import com.example.powermockdemo.note.entity.UserDO;
import com.example.powermockdemo.note.dao.UserDao;
import com.example.powermockdemo.note.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * user对象相关的逻辑服务实现类
 * 注入了带final关键字的UserDao
 *
 * @author : liuyaodong
 * @date 2022/5/12
 */
@Service(value = "userServiceWithFinalImpl")
@Slf4j
public class UserServiceWithFinalImpl implements UserService {

    @Autowired
    @Qualifier(value = "userDaoWithFinalImpl")
    private UserDao userDao;

    @Override
    public int queryUserCount() {
        return userDao.getUserCount();
    }

    @Override
    public void saveUser(UserDO userDO) {
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
