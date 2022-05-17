package com.example.powermockdemo.note.service.user.impl;

import com.example.powermockdemo.note.entity.UserDO;
import com.example.powermockdemo.note.dao.UserDao;
import com.example.powermockdemo.note.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * user对象相关的逻辑服务实现类
 * 通过构造器加载UserDao
 *
 * @author : liuyaodong
 * @date 2022/5/12
 */
@Service(value = "userServiceWithConstructorImpl")
@Slf4j
public class UserServiceWithConstructorImpl implements UserService {

    private final UserDao userDao;

    public UserServiceWithConstructorImpl(@Qualifier(value = "userDaoImpl") UserDao userDao) {
        this.userDao = userDao;
    }

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
        isOk();
        userDao.getUserCount();
        userDao.insertUser(userDO);
        userDao.queryUserPhoneNumber(userDO);
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
        if(isOk()){
            return userDao.queryUserPhoneNumber(userDO);
        }else {
            return "";
        }

    }

    private boolean isOk(){
        return true;
    }
}
