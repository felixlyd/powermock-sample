package com.example.powermockdemo.service.impl;

import com.example.powermockdemo.dao.dos.impl.UserDaoArgumentMatch;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/5/9
 */
public class UserServiceAnswerImplTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testQueryUserPhoneNumber() {
        UserDaoArgumentMatch userDaoArgumentMatch = PowerMockito.mock(UserDaoArgumentMatch.class);

        String userNameA = "zhangSan";
        String phoneNumberA = "123456";

        String userNameB = "liSi";
        String phoneNumberB = "789";

        PowerMockito.doAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {

                String arg = invocationOnMock.getArgument(0);
                if(arg.equals(userNameA)){
                    return phoneNumberA;
                } else if (arg.equals(userNameB)) {
                    return phoneNumberB;
                }else {
                    throw new RuntimeException("未知参数");
                }
            }
        }).when(userDaoArgumentMatch).getPhoneNumber(Mockito.anyString());

        PowerMockito.doReturn(phoneNumberA).when(userDaoArgumentMatch).getPhoneNumber(Mockito.argThat(str->str.equals(userNameA)));
        PowerMockito.doReturn(phoneNumberB).when(userDaoArgumentMatch).getPhoneNumber(Mockito.argThat(str->str.equals(userNameB)));

        UserServiceAnswerImpl userServiceAnswer = new UserServiceAnswerImpl();
        Whitebox.setInternalState(userServiceAnswer, "userDaoArgumentMatch", userDaoArgumentMatch);

        String userPhoneNumber = userServiceAnswer.queryUserPhoneNumber(userNameA);
        Assert.assertEquals(phoneNumberA, userPhoneNumber);

        userPhoneNumber = userServiceAnswer.queryUserPhoneNumber(userNameB);
        Assert.assertEquals(phoneNumberB, userPhoneNumber);
    }
}