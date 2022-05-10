package com.example.powermockdemo.learn.service;

import com.example.powermockdemo.learn.dao.vdo.UserDO;
import org.springframework.stereotype.Service;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/3/7
 */
@Service
public interface UserService {

    /**
     * 查询并返回所有user数目
     * @return 所有user数目
     */
    int queryUserCount();

    /**
     * 插入一条user数据
     * @param userDO user数据对象
     */
    void saveUser(UserDO userDO);
}
