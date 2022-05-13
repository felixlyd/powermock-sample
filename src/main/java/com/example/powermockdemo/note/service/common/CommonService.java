package com.example.powermockdemo.note.service.common;

import com.example.powermockdemo.note.entity.UserDO;
import lombok.extern.slf4j.Slf4j;

/**
 * 含有静态方法的公共类
 *
 * @author : liuyaodong
 * @date 2022/5/12
 */
@Slf4j
public class CommonService {
    /**
     * 静态的返回数字
     * @return 返回数字
     */
    public static int getNumbers(){
        return -1;
    }
    /**
     * 静态的void方法
     * @param userDO 用户数据对象
     */
    public static void doNothing(UserDO userDO){}

    /**
     * 返回数目1
     * 同时具备非静态方法
     * @return 数目1
     */
    public int getRealNumbers(){
        log.info("测试do...when...和when...then...的区别");
        return 1;
    }

}
