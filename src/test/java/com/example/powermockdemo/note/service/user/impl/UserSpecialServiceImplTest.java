package com.example.powermockdemo.note.service.user.impl;

import com.example.powermockdemo.note.service.common.SpecialService;
import com.example.powermockdemo.note.service.user.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.*;

/**
 * demo-19：如果“静态类”中有需要初始化的私有属性怎么办？
 *
 * @author : liuyaodong
 * @date 2022/5/23
 */
@RunWith(PowerMockRunner.class)
@SuppressStaticInitializationFor("com.example.powermockdemo.note.service.common.SpecialService")
@PrepareForTest({SpecialService.class})
public class UserSpecialServiceImplTest {

    @Test
    public void testQueryUserCount() throws Exception {
        PowerMockito.mockStatic(SpecialService.class);
        PowerMockito.doReturn(1).when(SpecialService.class,"getNumbers");
        UserService userService = new UserSpecialServiceImpl();
        int num = userService.queryUserCount();
        Assert.assertEquals(1, num);
    }
}