package com.example.powermocklearndemo.dao.dos.impl;

import com.example.powermocklearndemo.dao.dos.UserDataService;
import com.example.powermocklearndemo.dao.vdo.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/4/18
 */
@Slf4j
@Service("finalUserDataServiceImpl")
final public class UserDataServiceFinalImpl implements UserDataService {
    @Override
    public int getUserCount() {
        log.info("test");
        throw new UnsupportedOperationException();
    }

    @Override
    public void insertUser(UserDO userDO) {
        throw new UnsupportedOperationException();
    }
}
