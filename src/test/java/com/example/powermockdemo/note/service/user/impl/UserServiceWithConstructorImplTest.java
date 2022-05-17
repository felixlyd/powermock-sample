package com.example.powermockdemo.note.service.user.impl;

import com.example.powermockdemo.note.dao.impl.UserDaoImpl;
import com.example.powermockdemo.note.dao.impl.UserDaoWithConstructorImpl;
import com.example.powermockdemo.note.entity.UserDO;
import com.example.powermockdemo.note.service.user.UserService;
import com.example.powermockdemo.note.dao.UserDao;
import com.example.powermockdemo.note.dao.impl.UserDaoMockImpl;
import com.example.powermockdemo.note.service.user.impl.test.MyAnswer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/5/12
 */
@Slf4j
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserServiceWithConstructorImpl.class)
public class UserServiceWithConstructorImplTest {

    /**
     * demo-01：自定义mock的单元测试
     */
    @Test
    public void testQueryUserCountWithJunit() {
        UserDao userDao = new UserDaoMockImpl();
        UserService userService = new UserServiceWithConstructorImpl(userDao);
        Assert.assertEquals(1, userService.queryUserCount());
    }

    /**
     * demo-03: 通过构造函数加载
     */
    @Test
    public void testQueryUserCount() {
        // mock类
        UserDao userDao = PowerMockito.mock(UserDaoImpl.class);
        // 模拟方法
        PowerMockito.doReturn(10).when(userDao).getUserCount();
        // 通过构造函数传参
        UserService userService = new UserServiceWithConstructorImpl(userDao);
        // 调用并验证
        Assert.assertEquals(1, userService.queryUserCount());
    }

    /**
     * demo-14: 通过自定义匹配根据不同的参数来返回不同的值
     */
    @Test
    public void testQueryUserPhoneNumber(){
        // mock类
        UserDao userDao = PowerMockito.mock(UserDaoImpl.class);

        String userNameA = "zhangSan";
        String phoneNumberA = "123456";

        String userNameB = "liSi";
        String phoneNumberB = "789";

        PowerMockito.doReturn(phoneNumberA).when(userDao).queryUserPhoneNumber(Mockito.argThat(user->user.getName().equals(userNameA)));
        PowerMockito.doReturn(phoneNumberB).when(userDao).queryUserPhoneNumber(Mockito.argThat(user->user.getName().equals(userNameB)));

        // 通过构造函数传参
        UserService userService = new UserServiceWithConstructorImpl(userDao);

        UserDO userDO = new UserDO();
        userDO.setName(userNameA);
        UserDO userDO1 = new UserDO();
        userDO1.setName(userNameB);
        String userPhoneNumber = userService.queryUserPhoneNumber(userDO);
        Assert.assertEquals(phoneNumberA, userPhoneNumber);

        userPhoneNumber = userService.queryUserPhoneNumber(userDO1);
        Assert.assertEquals(phoneNumberB, userPhoneNumber);
    }

    /**
     * demo-15: 实现Mocktio.Answer接口，根据参数自定义应答
     */
    @Test
    public void testQueryUserPhoneNumberWithAnswer(){
        // mock类
        UserDao userDao = PowerMockito.mock(UserDaoImpl.class);

        String userNameA = "zhangSan";
        String phoneNumberA = "123456";

        String userNameB = "liSi";
        String phoneNumberB = "789";

        // 实现Answer
        PowerMockito.doAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {

                UserDO arg = invocationOnMock.getArgument(0);
                if(arg.getName().equals(userNameA)){
                    return phoneNumberA;
                } else if (arg.getName().equals(userNameB)) {
                    return phoneNumberB;
                }else {
                    throw new RuntimeException("未知参数");
                }
            }
        }).when(userDao).queryUserPhoneNumber(Mockito.any(UserDO.class));

        // 通过构造函数传参
        UserService userService = new UserServiceWithConstructorImpl(userDao);

        UserDO userDO = new UserDO();
        userDO.setName(userNameA);
        String userPhoneNumber = userService.queryUserPhoneNumber(userDO);
        Assert.assertEquals(phoneNumberA, userPhoneNumber);

        UserDO userDO1 = new UserDO();
        userDO1.setName(userNameB);
        userPhoneNumber = userService.queryUserPhoneNumber(userDO1);
        Assert.assertEquals(phoneNumberB, userPhoneNumber);
    }

    /**
     * demo-16: 实现Mocktio.Answer接口，保存中间变量并取出
     */
    @Test
    public void testQueryUserPhoneNumberWithAnswer2(){
        // mock类
        UserDao userDao = PowerMockito.mock(UserDaoImpl.class);

        String userNameA = "zhangSan";
        String phoneNumberA = "123456";

        // 实现Answer
        MyAnswer myAnswer = new MyAnswer();

        PowerMockito.doAnswer(myAnswer).when(userDao).queryUserPhoneNumber(Mockito.any(UserDO.class));

        // 通过构造函数传参
        UserService userService = new UserServiceWithConstructorImpl(userDao);

        UserDO userDO = new UserDO();
        userDO.setName(userNameA);
        String userPhoneNumber = userService.queryUserPhoneNumber(userDO);
        Assert.assertEquals(phoneNumberA, userPhoneNumber);

        log.info(myAnswer.getUserDO().toString());
    }

    /**
     * demo-17: private关键字示例
     */
    @Test
    public void testQueryUserPhoneNumberWithPrivateMock() throws Exception {

        UserService userService = PowerMockito.spy(new UserServiceWithConstructorImpl(new UserDaoImpl()));
        try {
            PowerMockito.doReturn(false).when(userService, "isOk");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        UserDO userDO = new UserDO();
        userDO.setName("zhangSan");
        String userPhoneNumber = userService.queryUserPhoneNumber(userDO);
        Assert.assertEquals("", userPhoneNumber);
        // 验证私有方法
        PowerMockito.verifyPrivate(userService,Mockito.times(1)).invoke("isOk");
    }

    /**
     * demo-18: 顺序验证
     */
    @Test
    public void testSaveUserWithPrivateMethod() throws Exception {

        UserDao userDao = PowerMockito.mock(UserDaoWithConstructorImpl.class);

        UserService userService = PowerMockito.spy(new UserServiceWithConstructorImpl(userDao));

        UserDO userDO = new UserDO();
        userDO.setName("zhangSan");
        userService.saveUserWithPrivateMethod(userDO);
        InOrder inOrder = Mockito.inOrder(userDao);
        inOrder.verify(userDao).getUserCount();
        inOrder.verify(userDao).insertUser(userDO);
        inOrder.verify(userDao).queryUserPhoneNumber(userDO);
    }

}