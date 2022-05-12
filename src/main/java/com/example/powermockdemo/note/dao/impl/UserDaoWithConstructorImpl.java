package com.example.powermockdemo.note.dao.impl;

import com.example.powermockdemo.learn.dao.vdo.UserDO;
import com.example.powermockdemo.note.constants.ErrorCodeEnum;
import com.example.powermockdemo.note.dao.UserDao;
import org.apache.commons.lang3.StringUtils;

/**
 * 操作数据库中user表的实现类
 * 由于某些特殊原因，带有构造器
 *
 * @author : liuyaodong
 * @date 2022/5/12
 */
public class UserDaoWithConstructorImpl implements UserDao {
    private String username;
    private String password;

    public UserDaoWithConstructorImpl(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int getUserCount() {
        return 0;
    }

    @Override
    public void insertUser(UserDO userDO) {
        if(StringUtils.equals(userDO.getName(), username)){
            throw new UnsupportedOperationException(ErrorCodeEnum.CANNOT_ACCESS_DB.getValue());
        }else {
            throw new RuntimeException(ErrorCodeEnum.CANNOT_QUERY_DB.getValue());
        }
    }

    @Override
    public String queryUserPhoneNumber(UserDO userDO) {
        return null;
    }
}
