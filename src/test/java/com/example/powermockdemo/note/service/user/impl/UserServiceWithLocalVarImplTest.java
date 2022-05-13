package com.example.powermockdemo.note.service.user.impl;

import com.example.powermockdemo.note.dao.impl.UserDaoImpl;
import com.example.powermockdemo.note.service.user.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/5/13
 */
// **必须**
@RunWith(PowerMockRunner.class)
// 准备需修改字节码的class：引入局部变量类的类
@PrepareForTest(UserServiceWithLocalVarImpl.class)
public class UserServiceWithLocalVarImplTest {

    /**
     * demo-03: 局部变量new关键字
     */
    @Test
    public void testQueryUserCount() {
        // mock类
        UserDaoImpl userDaoImpl = PowerMockito.mock(UserDaoImpl.class);
        try {
            // 当new一个类时，引入该类
            PowerMockito.whenNew(UserDaoImpl.class).withNoArguments().thenReturn(userDaoImpl);
            // 模拟方法
            PowerMockito.doReturn(10).when(userDaoImpl).getUserCount();
            UserService userService = new UserServiceWithLocalVarImpl();
            // 调用并验证
            Assert.assertEquals(10, userService.queryUserCount());
        } catch (Exception e) {
            Assert.fail("测试失败");
        }

    }
}