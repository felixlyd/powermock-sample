package com.example.powermocklearndemo.dao.dos.impl;

import com.example.powermocklearndemo.dao.dos.UserDataService;
import com.example.powermocklearndemo.dao.vdo.UserDO;
import lombok.extern.slf4j.Slf4j;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/3/7
 */
@Slf4j
public class UserDataServiceImpl implements UserDataService {

    @Override
    public int getUserCount() {
        log.info("test");
        throw new UnsupportedOperationException();
    }

    @Override
    public void insertUser(UserDO userDO){
        throw new UnsupportedOperationException();
    }
}
