package com.example.powermockdemo.note.service.user.impl.test;

import com.example.powermockdemo.note.entity.UserDO;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/5/13
 */
public class MyAnswer implements Answer<String> {
    private final UserDO userDO = new UserDO();

    @Override
    public String answer(InvocationOnMock invocationOnMock) throws Throwable {
        UserDO arg = invocationOnMock.getArgument(0);
        userDO.setName(arg.getName());
        if(arg.getName().equals("zhangSan")){
            return "123456";
        } else {
            throw new RuntimeException("未知参数");
        }
    }

    public UserDO getUserDO() {
        return userDO;
    }
}
