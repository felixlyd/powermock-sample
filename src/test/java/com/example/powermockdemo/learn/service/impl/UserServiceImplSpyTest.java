package com.example.powermockdemo.learn.service.impl;

import com.example.powermockdemo.learn.dao.dos.impl.UserDataServiceImpl;
import com.example.powermockdemo.learn.service.UserService;
import com.example.powermockdemo.learn.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/5/9
 */
@Slf4j
public class UserServiceImplSpyTest {

    @Test
    public void testQueryUserCount() {
        UserDataServiceImpl userDataService = PowerMockito.mock(UserDataServiceImpl.class);
        UserService service = new UserServiceImpl(userDataService);
        int result = service.queryUserCount();
        log.info(String.valueOf(result));
    }

    @Test
    public void testQueryUserCountWithSpy() {
        UserDataServiceImpl userDataService = PowerMockito.spy(new UserDataServiceImpl());
        // 如果注释下一行代码即不指定mock操作，那么spy会去执行真实的函数。
        // PowerMockito.spy：如果不指定mock操作，会执行真实的操作。如果使用PowerMockito.mock则不会。
        PowerMockito.doReturn(10).when(userDataService).getUserCount();
        UserService service = new UserServiceImpl(userDataService);
        int result = service.queryUserCount();
        log.info(String.valueOf(result));
    }

}