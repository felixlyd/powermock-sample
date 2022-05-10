package com.example.powermocklearndemo.dao.dos.impl;

import com.example.powermocklearndemo.dao.vdo.UserDO;
import lombok.extern.slf4j.Slf4j;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/4/19
 */
@Slf4j
public class UserDaoVerify {
    public int getUserCount() {
        log.info("test");
        throw new UnsupportedOperationException();
    }
    public void saveUser(UserDO userDO){
        log.info("test");
    }
    public void updateUser(UserDO userDO){
        log.info("test");
    }
}
