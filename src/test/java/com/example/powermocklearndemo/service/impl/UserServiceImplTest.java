package com.example.powermocklearndemo.service.impl;

import com.example.powermocklearndemo.dao.dos.UserDataService;
import com.example.powermocklearndemo.dao.dos.impl.UserDataServiceImpl;
import com.example.powermocklearndemo.dao.vdo.UserDO;
import com.example.powermocklearndemo.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;

import static org.junit.Assert.*;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/3/7
 */
public class UserServiceImplTest {

    private UserService userService;

    // 当前类测试方法执行前，统一执行的方法
    @Before
    public void setUp(){
        UserDataService userDataService = new UserDataServiceImpl();
        userService = new UserServiceImpl(userDataService);
   }

   // 忽略一些单元测试
   @Ignore
    // 自定义的异常测试
    @Test
    public void queryUserCountWithJunit() {
        try{
            int count = userService.queryUserCount();
            assertEquals(0, count);
            fail("执行不到这里");
        }catch (Exception e){
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }

    /**
     * junit风格的异常测试
     */
    @Ignore
    @Test(expected = UnsupportedOperationException.class)
    public void saveUserWithJunit() {
            userService.saveUser(new UserDO());
    }

    /**
     * 基于Mockito的单元测试，添加被Mock的对象
     */
    @Mock
    private UserDataService userDataService;

    /**
     * 基于Mockito的单元测试
     */
    @Ignore
    @Test
    public void testQueryUserCountWithMockito(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(userDataService.getUserCount()).thenReturn(10);

        UserService service = new UserServiceImpl(userDataService);
        int result = service.queryUserCount();
        assertEquals(10, result);
    }

    /**
     * 基于powermock的测试
     * do...when...then...
     */
    @Test
    public void testQueryUserCountWithPowerMock(){
        UserDataService userDao = PowerMockito.mock(UserDataService.class);
//        PowerMockito.doReturn(10).when(userDao.getUserCount()); 错误写法
        PowerMockito.doReturn(10).when(userDao).getUserCount();
        UserService service = new UserServiceImpl(userDao);
        int result = service.queryUserCount();
        assertEquals(10, result);
    }

    /**
     * 基于powermock的测试
     * when...(do)..then...
     */
    @Test
    public void testQueryUserCountWithPowerMock2(){
        UserDataService userDao = PowerMockito.mock(UserDataService.class);
        PowerMockito.when(userDao.getUserCount()).thenReturn(10);
        UserService service = new UserServiceImpl(userDao);
        int result = service.queryUserCount();
        assertEquals(10, result);
    }

    /**
     * verify的使用--对无返回值的验证策略
     * 如果不传入Mockito.times()，则默认为1次
     * 这里建议使用Mockito的verify方式，更直观明了
     * @throws Exception 为使用Powermock抛出的异常
     */
    @Test
    public void testInsertUserWithPowerMock() throws Exception {
        UserDataService userDao = PowerMockito.mock(UserDataService.class);

        PowerMockito.doNothing().when(userDao).insertUser(Mockito.any(UserDO.class));
        UserService service = new UserServiceImpl(userDao);
        UserDO userDO = new UserDO();
        service.saveUser(userDO);
        Mockito.verify(userDao, Mockito.times(1)).insertUser(userDO);
    }
}