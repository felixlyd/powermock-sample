package com.example.powermockdemo.note.dao.impl;

import com.example.powermockdemo.note.entity.UserDO;
import com.example.powermockdemo.note.constants.ErrorCodeEnum;
import com.example.powermockdemo.note.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 操作数据库中user表的实现类mock
 *
 * @author : liuyaodong
 * @date 2022/5/12
 */
@Slf4j
public class UserDaoMockImpl extends UserDaoImpl {

    @Override
    public int getUserCount() {
        log.info("测试spy和mock的区别");
        return 1;
    }

    @Override
    public void insertUser(UserDO userDO) {
        log.info("ok");
    }

    @Override
    public String queryUserPhoneNumber(UserDO userDO) {
        return "123456789";
    }
}
