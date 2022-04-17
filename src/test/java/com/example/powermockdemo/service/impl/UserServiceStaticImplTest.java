package com.example.powermockdemo.service.impl;

import com.example.powermockdemo.common.CommonService;
import com.example.powermockdemo.dao.vdo.TeacherDO;
import com.example.powermockdemo.dao.vdo.UserDO;
import com.example.powermockdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/3/14
 */
@Slf4j
@RunWith(PowerMockRunner.class)
@PrepareForTest(CommonService.class)
public class UserServiceStaticImplTest {

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserServiceStaticImpl();
        PowerMockito.mockStatic(CommonService.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testQueryUserCount() {
        try {
//            PowerMockito.when(CommonService.getNumbers()).thenReturn(10);
            PowerMockito.doReturn(10).when(CommonService.class,"getNumbers");
            int result = userService.queryUserCount();
            assertEquals(10, result);
        } catch (Exception e) {
            fail("测试失败");
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveUser() {
        try {
            UserDO userDO = new UserDO();
            PowerMockito.doNothing().when(CommonService.class,"doSomething", Mockito.any(UserDO.class));
            userService.saveUser(userDO);
            PowerMockito.verifyStatic(CommonService.class, Mockito.times(1));
            userService.saveUser(Mockito.any(UserDO.class));
    } catch (Exception e) {
            fail("测试失败");
            e.printStackTrace();
        }
    }

    @Test
    public void testExtends(){
        TeacherDO teacherDO = new TeacherDO();
        teacherDO.setTeachLesson("math");
        teacherDO.setName("zhangSan");
        UserDO userDO = (UserDO) teacherDO;
        log.info(userDO.getName());
        TeacherDO teacherDO2 = new TeacherDO();
        teacherDO2.setTeachLesson("English");
        TeacherDO teacherDO1 = (TeacherDO) userDO;
        log.info(teacherDO1.getTeachLesson());
        assertEquals("1","1");
    }
}