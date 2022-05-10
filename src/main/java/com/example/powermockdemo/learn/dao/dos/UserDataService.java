package com.example.powermockdemo.learn.dao.dos;

import com.example.powermockdemo.learn.dao.vdo.UserDO;
import org.springframework.stereotype.Service;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/3/7
 */
@Service
public interface UserDataService {
    /**
     * 查询所有user返回数量
     * @return user的数量
     */
    int getUserCount();

    /**
     * 插入一条user数据
     * @param userDO user数据对象
     */
    void insertUser(UserDO userDO);
}
