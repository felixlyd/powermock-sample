package com.example.powermockdemo.service.impl;

import com.example.powermockdemo.dao.dos.impl.TestVerifyUserDao;
import com.example.powermockdemo.dao.vdo.UserDO;
import org.junit.After;
import org.junit.Before;
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
 * @date 2022/4/19
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({TestVerifyUserService.class})
public class TestVerifyUserServiceTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSaveOrUpdateWillUseSaveUser() throws Exception {
        TestVerifyUserDao userDao = PowerMockito.mock(TestVerifyUserDao.class);
        PowerMockito.whenNew(TestVerifyUserDao.class).withAnyArguments().thenReturn(userDao);
        PowerMockito.doReturn(1).when(userDao).getUserCount();

        UserDO userDO = new UserDO();
        TestVerifyUserService userService = new TestVerifyUserService();
        userService.saveOrUpdate(userDO);
        Mockito.verify(userDao, Mockito.never()).saveUser(userDO);
        Mockito.verify(userDao).updateUser(userDO);
    }

    @Test
    public void testSaveOrUpdateWillUseUpdateUser() throws Exception {
        TestVerifyUserDao userDao = PowerMockito.mock(TestVerifyUserDao.class);
        PowerMockito.whenNew(TestVerifyUserDao.class).withAnyArguments().thenReturn(userDao);
        PowerMockito.doReturn(0).when(userDao).getUserCount();

        UserDO userDO = new UserDO();
        TestVerifyUserService userService = new TestVerifyUserService();
        userService.saveOrUpdate(userDO);
        Mockito.verify(userDao).saveUser(userDO);
        Mockito.verify(userDao, Mockito.never()).updateUser(userDO);
    }
}