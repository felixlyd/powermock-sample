package com.example.powermockdemo.note.service.user.impl;

import com.example.powermockdemo.note.entity.UserDO;
import com.example.powermockdemo.note.constants.ErrorCodeEnum;
import com.example.powermockdemo.note.dao.UserDao;
import com.example.powermockdemo.note.service.common.CommonService;
import com.example.powermockdemo.note.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * user对象相关的逻辑服务实现类
 * 通过@Autowired注入UserDao
 *
 * @author : liuyaodong
 * @date 2022/5/12
 */
@Service(value = "userServiceImpl")
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier(value = "userDaoImpl")
    private UserDao userDao;

    @Override
    public int queryUserCount() {
        log.info("--user数目查询中--");
        return userDao.getUserCount();
    }

    @Override
    public void saveUser(UserDO userDO) {
        log.info("--user对象新增中--");
        userDao.insertUser(userDO);
    }

    @Override
    public int queryUserCountWithPrivateMethod() {
        if(isOk()){
            return userDao.getUserCount();
        }else {
            throw new RuntimeException(ErrorCodeEnum.CANNOT_QUERY_DB.getValue());
        }
    }

    @Override
    public void saveUserWithPrivateMethod(UserDO userDO) {
        if(isOk()){
            userDao.getUserCount();
        }else {
            throw new RuntimeException(ErrorCodeEnum.CANNOT_QUERY_DB.getValue());
        }
    }

    @Override
    public int queryUserCountWithStaticMethod() {
        return CommonService.getNumbers();
    }

    @Override
    public void saveUserWithStaticMethod(UserDO userDO) {
        CommonService.doNothing(userDO);
    }

    @Override
    public String queryUserPhoneNumber(UserDO userDO) {
        if(isOk()){
            CommonService commonService = new CommonService();
            return String.valueOf(commonService.getRealNumbers()+CommonService.getNumbers());
        }
       return null;
    }

    private boolean isOk(){
        return true;
    }

    public int getRealNumbers(){
        CommonService commonService = new CommonService();
        return commonService.getRealNumbers();
    }
}
