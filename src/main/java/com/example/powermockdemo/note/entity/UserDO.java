package com.example.powermockdemo.note.entity;


/**
 * 数据库中user表的数据对象
 * 实际运用中，有数据库需加@Entity注解
 *
 * @author : liuyaodong
 * @date 2022/3/7
 */
public class UserDO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "name='" + name + '\'' +
                '}';
    }
}
