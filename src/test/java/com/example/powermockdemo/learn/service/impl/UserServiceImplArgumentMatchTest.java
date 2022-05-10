package com.example.powermockdemo.learn.service.impl;

import com.example.powermockdemo.learn.dao.dos.impl.UserDaoArgumentMatch;
import com.example.powermockdemo.learn.service.impl.UserServiceArgumentMatchImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/4/27
 */
public class UserServiceImplArgumentMatchTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testQueryUserCountWithArgumentMatch() throws Exception {
        UserDaoArgumentMatch userDaoArgumentMatch = PowerMockito.mock(UserDaoArgumentMatch.class);

        String userNameA = "zhangSan";
        String phoneNumberA = "123456";

        String userNameB = "liSi";
        String phoneNumberB = "789";

        //Mockito.argThat 参数规则匹配：当满足XXX条件时，指定特定mock操作
        PowerMockito.doReturn(phoneNumberA).when(userDaoArgumentMatch).getPhoneNumber(Mockito.argThat(str->str.equals(userNameA)));
        PowerMockito.doReturn(phoneNumberB).when(userDaoArgumentMatch).getPhoneNumber(Mockito.argThat(str->str.equals(userNameB)));

        UserServiceArgumentMatchImpl userServiceArgumentMatch = new UserServiceArgumentMatchImpl();
        Whitebox.setInternalState(userServiceArgumentMatch, "userDaoArgumentMatch", userDaoArgumentMatch);

        String userPhoneNumber = userServiceArgumentMatch.queryUserPhoneNumber(userNameA);
        Assert.assertEquals(phoneNumberA, userPhoneNumber);

        userPhoneNumber = userServiceArgumentMatch.queryUserPhoneNumber(userNameB);
        Assert.assertEquals(phoneNumberB, userPhoneNumber);
    }
}
