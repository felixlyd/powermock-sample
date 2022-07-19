package com.example.powermockdemo.note.service.user.impl;

import com.example.powermockdemo.note.dao.impl.UserDaoImpl;
import com.example.powermockdemo.note.service.user.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * demo-19: Mockito提供的注解
 *
 * @author : liuyaodong
 * @date 2022/5/13
 */
public class UserServiceWithMockitoImplTest {

    // 需被mock的类
    @Mock
    private  UserDaoImpl userDaoImpl;

    @Mock
    private  UserDaoImpl userDaoImpl2;

    // 需要注入mock类的类
    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Test
    public void testQueryUserCount() {
        // 将@Mock注解的类注入到@InjectMocks注解的类
        MockitoAnnotations.initMocks(this);
        // 模拟方法
        Mockito.doReturn(10).when(userDaoImpl).getUserCount();
        // 调用模拟方法
        int result = userService.queryUserCount();
        // 验证
        Assert.assertEquals(10, result);
    }
}