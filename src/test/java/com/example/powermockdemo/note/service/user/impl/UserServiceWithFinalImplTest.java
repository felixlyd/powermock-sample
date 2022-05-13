package com.example.powermockdemo.note.service.user.impl;

import com.example.powermockdemo.note.dao.UserDao;
import com.example.powermockdemo.note.dao.impl.UserDaoImpl;
import com.example.powermockdemo.note.service.user.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.*;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/5/13
 */
// **必须**
@RunWith(PowerMockRunner.class)
// 准备需修改字节码的class：引入final类的类
@PrepareForTest(UserServiceWithFinalImpl.class)
public class UserServiceWithFinalImplTest {

    /**
     * demo-05: 对final关键字的类进行Mock
     */
    @Test
    public void testQueryUserCount() {
        // mock类
        UserDao userDao = PowerMockito.mock(UserDaoImpl.class);
        // 模拟方法
        PowerMockito.doReturn(10).when(userDao).getUserCount();
        UserService userService = new UserServiceImpl();
        // 注入到UserService
        Whitebox.setInternalState(userService, "userDao", userDao);
        // 调用模拟方法
        int result = userService.queryUserCount();
        // 验证
        Assert.assertEquals(10, result);
    }
}