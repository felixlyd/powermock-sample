package com.example.powermockdemo.learn.service.impl;

import com.example.powermockdemo.learn.dao.dos.impl.UserDataServiceFinalImpl;
import com.example.powermockdemo.learn.service.UserService;
import com.example.powermockdemo.learn.service.impl.UserServiceFinalImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/4/18
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({UserServiceFinalImpl.class, UserDataServiceFinalImpl.class})
public class UserServiceFinalImplTest {

    @Mock
    private UserDataServiceFinalImpl userDataService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testQueryUserCountWithMockito() {
        MockitoAnnotations.initMocks(this);
        UserService userService = new UserServiceFinalImpl(userDataService);
        Mockito.doReturn(10).when(userDataService).getUserCount();
        int result = userService.queryUserCount();
        assertEquals(10, result);
    }

    @Ignore
    @Test
    public void testSaveUser() {
    }

    @Test
    public void testQueryUserCountWithPowerMock() {
        UserDataServiceFinalImpl userDataService1 = PowerMockito.mock(UserDataServiceFinalImpl.class);
        UserService userService = new UserServiceFinalImpl(userDataService1);
        Mockito.doReturn(10).when(userDataService1).getUserCount();
        int result = userService.queryUserCount();
        assertEquals(10, result);
    }
}