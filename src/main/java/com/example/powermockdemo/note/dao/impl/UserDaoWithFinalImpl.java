package com.example.powermockdemo.note.dao.impl;

import com.example.powermockdemo.learn.dao.vdo.UserDO;
import com.example.powermockdemo.note.constants.ErrorCodeEnum;
import com.example.powermockdemo.note.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 操作数据库中user表的带有final关键字的实现类
 *
 * @author : liuyaodong
 * @date 2022/5/12
 */
@Service(value = "userDaoWithFinalImpl")
@Slf4j
final public class UserDaoWithFinalImpl implements UserDao {

    @Override
    public int getUserCount() {
        log.info("测试spy和mock的区别");
        throw new UnsupportedOperationException(ErrorCodeEnum.CANNOT_ACCESS_DB.getValue());
    }

    @Override
    public void insertUser(UserDO userDO) {
        throw new UnsupportedOperationException(ErrorCodeEnum.CANNOT_ACCESS_DB.getValue());
    }

    @Override
    public String queryUserPhoneNumber(UserDO userDO) {
        throw new UnsupportedOperationException(ErrorCodeEnum.CANNOT_ACCESS_DB.getValue());
    }
}
