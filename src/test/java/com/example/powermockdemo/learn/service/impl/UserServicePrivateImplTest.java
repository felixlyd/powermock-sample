package com.example.powermockdemo.learn.service.impl;

import com.example.powermockdemo.learn.dao.dos.impl.UserDaoArgumentMatch;
import com.example.powermockdemo.learn.service.impl.UserServicePrivateImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/5/9
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserServicePrivateImpl.class)
public class UserServicePrivateImplTest {

    @Test
    public void testQueryUserPhoneNumberWithPrivateMock() {

        String userNameA = "zhangSan";
        String phoneNumberA = "123456";

        UserServicePrivateImpl userServicePrivate = PowerMockito.spy(new UserServicePrivateImpl());
        try {
            PowerMockito.doReturn(true).when(userServicePrivate, "isOk");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        UserDaoArgumentMatch userDaoArgumentMatch = PowerMockito.mock(UserDaoArgumentMatch.class);
        PowerMockito.doReturn(phoneNumberA).when(userDaoArgumentMatch).getPhoneNumber(userNameA);
        Whitebox.setInternalState(userServicePrivate,"userDaoArgumentMatch",userDaoArgumentMatch);

        String userPhoneNumber = userServicePrivate.queryUserPhoneNumber(userNameA);
        Assert.assertEquals(phoneNumberA, userPhoneNumber);
    }

    @Test
    public void testQueryUserPhoneNumber(){
        UserServicePrivateImpl userServicePrivate = new UserServicePrivateImpl();
        String phoneNumber = userServicePrivate.queryUserPhoneNumber("111");
        Assert.assertEquals("", phoneNumber);
    }
}