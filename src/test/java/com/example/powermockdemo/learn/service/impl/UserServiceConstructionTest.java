package com.example.powermockdemo.learn.service.impl;

import com.example.powermockdemo.learn.dao.dos.impl.UserDaoConstruction;
import com.example.powermockdemo.learn.service.impl.UserServiceConstruction;
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
 * @date 2022/4/26
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserServiceConstruction.class)
public class UserServiceConstructionTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSave() throws Exception {
        UserDaoConstruction userDaoConstruction = PowerMockito.mock(UserDaoConstruction.class);
        PowerMockito.whenNew(UserDaoConstruction.class).withArguments(Mockito.anyString(), Mockito.anyString()).thenReturn(userDaoConstruction);
        PowerMockito.doNothing().when(userDaoConstruction).insert();
        UserServiceConstruction userServiceConstruction = new UserServiceConstruction();
        String username = "zhangSan";
        String password = "123456";
        userServiceConstruction.save(username, password);
        Mockito.verify(userDaoConstruction,Mockito.times(1)).insert();
    }
}