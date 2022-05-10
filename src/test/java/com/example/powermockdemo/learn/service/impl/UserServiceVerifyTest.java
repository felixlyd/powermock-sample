package com.example.powermockdemo.learn.service.impl;

import com.example.powermockdemo.learn.dao.dos.impl.UserDaoVerify;
import com.example.powermockdemo.learn.dao.vdo.UserDO;
import com.example.powermockdemo.learn.service.impl.UserServiceVerify;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/4/19
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({UserServiceVerify.class})
public class UserServiceVerifyTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSaveOrUpdateWillUseSaveUser() throws Exception {
        UserDaoVerify userDao = PowerMockito.mock(UserDaoVerify.class);
        PowerMockito.whenNew(UserDaoVerify.class).withAnyArguments().thenReturn(userDao);
        PowerMockito.doReturn(1).when(userDao).getUserCount();

        UserDO userDO = new UserDO();
        UserServiceVerify userService = new UserServiceVerify();
        userService.saveOrUpdate(userDO);
        Mockito.verify(userDao, Mockito.never()).saveUser(userDO);
        Mockito.verify(userDao).updateUser(userDO);
    }

    @Test
    public void testSaveOrUpdateWillUseUpdateUser() throws Exception {
        UserDaoVerify userDao = PowerMockito.mock(UserDaoVerify.class);
        PowerMockito.whenNew(UserDaoVerify.class).withAnyArguments().thenReturn(userDao);
        PowerMockito.doReturn(0).when(userDao).getUserCount();

        UserDO userDO = new UserDO();
        UserServiceVerify userService = new UserServiceVerify();
        userService.saveOrUpdate(userDO);
        Mockito.verify(userDao).saveUser(userDO);
        Mockito.verify(userDao, Mockito.never()).updateUser(userDO);
    }
}