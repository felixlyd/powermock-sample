package com.example.powermockdemo.note.service.user;

import com.example.powermockdemo.learn.dao.vdo.UserDO;

/**
 * user对象相关的逻辑服务
 *
 * @author : liuyaodong
 * @date 2022/5/12
 */
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

    /**
     * 查询并返回所有user数目
     * 过程中运行了带private的方法
     * @return 所有user数目
     */
    int queryUserCountWithPrivateMethod();

    /**
     * 插入一条user数据
     * 过程中运行了带private的方法
     * @param userDO user数据对象
     */
    void saveUserWithPrivateMethod(UserDO userDO);

    /**
     * 查询并返回所有user数目
     * 过程中运行了带static的方法
     * @return 所有user数目
     */
    int queryUserCountWithStaticMethod();

    /**
     * 插入一条user数据
     * 过程中运行了带static的方法
     * @param userDO user数据对象
     */
    void saveUserWithStaticMethod(UserDO userDO);

    /**
     * 查询用户的手机号码
     * @param userDO user对象
     * @return 手机号码
     */
    String queryUserPhoneNumber(UserDO userDO);
}
