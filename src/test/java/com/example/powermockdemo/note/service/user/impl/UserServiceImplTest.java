package com.example.powermockdemo.note.service.user.impl;

import com.example.powermockdemo.note.dao.UserDao;
import com.example.powermockdemo.note.dao.impl.UserDaoImpl;
import com.example.powermockdemo.note.entity.UserDO;
import com.example.powermockdemo.note.service.common.CommonService;
import com.example.powermockdemo.note.service.user.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/5/13
 */
// **必须**
@RunWith(PowerMockRunner.class)
// 准备需修改字节码的class：静态类本身
@PrepareForTest({CommonService.class, UserServiceImpl.class})
public class UserServiceImplTest {

    /**
     * demo-02：基于powermock的单元测试
     */
    @Test
    public void testQueryUserCount() throws Exception {
        // mock类
        UserDao userDao = PowerMockito.mock(UserDaoImpl.class);
        // 模拟方法
//        PowerMockito.when(userDao.getUserCount()).thenReturn(10);
        PowerMockito.doReturn(10).when(userDao).getUserCount();
        UserService userService = new UserServiceImpl();
        // 注入到UserService
        Whitebox.setInternalState(userService, "userDao", userDao);
        // 调用模拟方法
        int result = userService.queryUserCount();
        // 验证
        Assert.assertEquals(10, result);
    }

    /**
     * demo-06: 对”静态类“进行mock
     */
    @Test
    public void testQueryUserCountWithStaticMethod() {
        PowerMockito.mockStatic(CommonService.class);
        UserService userService = new UserServiceImpl();
        try {
            PowerMockito.doReturn(10).when(CommonService.class,"getNumbers");
//            PowerMockito.when(CommonService.class,"getNumbers").thenReturn(10);
            Assert.assertEquals(10, userService.queryUserCountWithStaticMethod());
        } catch (Exception e) {
            Assert.fail("测试失败");
        }
    }

    /**
     * demo-07: 一个类中既有其他类提供的静态方法又有该类提供的普通方法
     */
    @Test
    public void testQueryUserPhoneNumber() throws Exception {
        PowerMockito.mockStatic(CommonService.class);
        CommonService commonService = PowerMockito.mock(CommonService.class);
        PowerMockito.whenNew(CommonService.class).withNoArguments().thenReturn(commonService);
        UserService userService = new UserServiceImpl();
        PowerMockito.doReturn(10).when(commonService).getRealNumbers();
        PowerMockito.doReturn(10).when(CommonService.class,"getNumbers");
        Assert.assertEquals("20", userService.queryUserPhoneNumber(new UserDO()));
    }

    /**
     * demo-08: 通过spy来“模拟”真实类，不模拟方法
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testUserServiceImplWithSpy() throws Exception{
        // spy一个类
        UserDao userDao = PowerMockito.spy(new UserDaoImpl());
        UserService userService = new UserServiceImpl();
        // 注入（也可以是局部变量whenNew和构造函数传参）
        Whitebox.setInternalState(userService, "userDao", userDao);
        // 不模拟方法，直接调用
        userService.queryUserCount();
    }

    /**
     * demo-09: 通过spy来“模拟”真实类，模拟方法
     */
    @Test
    public void testUserServiceImplWithSpy2(){
        // spy一个类
        UserDao userDao = PowerMockito.spy(new UserDaoImpl());
        UserService userService = new UserServiceImpl();
        // 注入（也可以是局部变量whenNew和构造函数传参）
        Whitebox.setInternalState(userService, "userDao", userDao);
        // 模拟方法
        PowerMockito.doReturn(1).when(userDao).getUserCount();
        Assert.assertEquals(1,userService.queryUserCount());
    }

    /**
     * demo-10: 测试PowerMockito.doThrow()
     */
    @Test(expected = RuntimeException.class)
    public void testUserServiceImplWithThrow() throws Exception{
        // mock一个类
        UserDao userDao = PowerMockito.mock(UserDaoImpl.class);
//        PowerMockito.when(userDao.getUserCount()).thenThrow(new RuntimeException());
        PowerMockito.doThrow(new RuntimeException()).when(userDao).getUserCount();
        UserService userService = new UserServiceImpl();
        Whitebox.setInternalState(userService, "userDao", userDao);
        userService.queryUserCount();
    }

    /**
     * demo-11: spy下的两种风格
     */
    @Test
    public void testUserServiceImplWithSpy3() throws Exception {
        // spy一个类
        CommonService commonService = PowerMockito.spy(new CommonService());
        UserServiceImpl userService = new UserServiceImpl();
        PowerMockito.whenNew(CommonService.class).withNoArguments().thenReturn(commonService);
//        // 模拟方法do...when...
//        PowerMockito.doReturn(2).when(commonService).getRealNumbers();
        // 模拟方法when...then...先执行代码逻辑
        PowerMockito.when(commonService.getRealNumbers()).thenReturn(2);
        Assert.assertEquals(2,userService.getRealNumbers());
    }

    /**
     * demo-12: 无返回值非静态verify验证
     */
    @Test
    public void testSaveUser(){
        UserDao userDao = PowerMockito.mock(UserDaoImpl.class);
        PowerMockito.doNothing().when(userDao).insertUser(Mockito.any(UserDO.class));
        UserService userService = new UserServiceImpl();
        Whitebox.setInternalState(userService, "userDao", userDao);
        userService.saveUser(new UserDO());
        // Mockito.times(1)代表调用1次，不传此参数默认为1次
        // Mockito.never()可以表示从未调用
        Mockito.verify(userDao, Mockito.times(1)).insertUser(Mockito.any(UserDO.class));
    }

    /**
     * demo-13: 无返回值静态verify验证
     */
    @Test
    public void testSaveUserWithStaticMethod(){
        PowerMockito.mockStatic(CommonService.class);
        UserService userService = new UserServiceImpl();
        try {
            PowerMockito.doNothing().when(CommonService.class,"doNothing", Mockito.any(UserDO.class));
            UserDO userDO = new UserDO();
            // 调用模拟方法
            userService.saveUserWithStaticMethod(userDO);
            PowerMockito.verifyStatic(CommonService.class, Mockito.times(1));
            // 告诉PowerMockito.verifyStatic需要验证的调用逻辑
            userService.saveUserWithStaticMethod(Mockito.any(UserDO.class));
        } catch (Exception e) {
            Assert.fail("测试失败");
        }
    }
}