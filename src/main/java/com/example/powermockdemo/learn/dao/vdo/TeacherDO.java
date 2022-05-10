package com.example.powermockdemo.learn.dao.vdo;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/3/23
 */
public class TeacherDO extends UserDO{
    private String teachLesson;

    public String getTeachLesson() {
        return teachLesson;
    }

    public void setTeachLesson(String teachLesson) {
        this.teachLesson = teachLesson;
    }
}
