package com.example.powermockdemo.note.dao;

import com.example.powermockdemo.note.entity.UserDO;

/**
 * 操作数据库中user表的接口
 *
 * @author : liuyaodong
 * @date 2022/5/12
 */
public interface UserDao {
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

    /**
     * 查询用户的手机号码
     * @param userDO user对象
     * @return 手机号码
     */
    String queryUserPhoneNumber(UserDO userDO);
}
