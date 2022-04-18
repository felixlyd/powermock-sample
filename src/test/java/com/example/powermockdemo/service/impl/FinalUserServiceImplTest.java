package com.example.powermockdemo.service.impl;

import com.example.powermockdemo.dao.dos.UserDataService;
import com.example.powermockdemo.dao.dos.impl.FinalUserDataServiceImpl;
import com.example.powermockdemo.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
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
@PrepareForTest({FinalUserServiceImpl.class,FinalUserDataServiceImpl.class})
public class FinalUserServiceImplTest {

    @Mock
    private FinalUserDataServiceImpl userDataService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testQueryUserCountWithMockito() {
        MockitoAnnotations.initMocks(this);
        UserService userService = new FinalUserServiceImpl(userDataService);
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
        FinalUserDataServiceImpl userDataService1 = PowerMockito.mock(FinalUserDataServiceImpl.class);
        UserService userService = new FinalUserServiceImpl(userDataService1);
        Mockito.doReturn(10).when(userDataService1).getUserCount();
        int result = userService.queryUserCount();
        assertEquals(10, result);
    }
}