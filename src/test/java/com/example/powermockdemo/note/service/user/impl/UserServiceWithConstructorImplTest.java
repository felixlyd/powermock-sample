package com.example.powermockdemo.note.service.user.impl;

import com.example.powermockdemo.note.service.user.UserService;
import com.example.powermockdemo.note.dao.UserDao;
import com.example.powermockdemo.note.dao.impl.UserDaoMockImpl;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/5/12
 */
public class UserServiceWithConstructorImplTest {

    /**
     * demo-01：自定义mock的单元测试
     */
    @Test
    public void testQueryUserCountWithJunit() {
        UserDao userDao = new UserDaoMockImpl();
        UserService userService = new UserServiceWithConstructorImpl(userDao);
        Assert.assertEquals(1, userService.queryUserCount());
    }
}