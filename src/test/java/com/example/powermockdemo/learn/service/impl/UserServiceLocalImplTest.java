package com.example.powermockdemo.learn.service.impl;

import com.example.powermockdemo.learn.dao.dos.impl.UserDataServiceImpl;
import com.example.powermockdemo.learn.dao.vdo.UserDO;
import com.example.powermockdemo.learn.service.UserService;
import com.example.powermockdemo.learn.service.impl.UserServiceLocalImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/3/14
 */
@Slf4j
@RunWith(PowerMockRunner.class)
@PrepareForTest({UserServiceLocalImpl.class})
public class UserServiceLocalImplTest {

    @Test
    public void testQueryUserCount() {
        try {
            UserDataServiceImpl userDataServiceImpl = PowerMockito.mock(UserDataServiceImpl.class);
            // 在new一个UserDataService的时候，就会触发下面的mock，new出来一个假的UserDataService
            // 必须在类之前增加注解@RunWith(PowerMockRunner.class)，相当于通过junit在运行时引入powermock改变字节码的功能
            // 必须在类之前增加注解@PrepareForTest({XXX.class})，相当于在运行前让junit准备好要加入这段mock类(或方法)的类的字节码
            // Powermock挡住的是实现具体方法的地方而不是声明方法的地方
            PowerMockito.whenNew(UserDataServiceImpl.class).withNoArguments().thenReturn(userDataServiceImpl);
            PowerMockito.doReturn(10).when(userDataServiceImpl).getUserCount();

            UserService userService = new UserServiceLocalImpl();
            int result = userService.queryUserCount();
            assertEquals(10, result);
        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testSaveUser() {
        try {
            UserDataServiceImpl userDataServiceImpl = PowerMockito.mock(UserDataServiceImpl.class);
            PowerMockito.whenNew(UserDataServiceImpl.class).withNoArguments().thenReturn(userDataServiceImpl);
            // 可以发现，两个方法中都需要对UserDataServiceImpl类构造mock，因此可以用@Before写在一处
            UserDO userDO = new UserDO();
            PowerMockito.doNothing().when(userDataServiceImpl).insertUser(userDO);

            UserService userService = new UserServiceLocalImpl();
            userService.saveUser(userDO);
            Mockito.verify(userDataServiceImpl).insertUser(userDO);

        }catch (Throwable e){
            fail(e.getMessage());
        }
    }
}