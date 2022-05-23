package com.example.powermockdemo.note.service.common;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/5/23
 */
public class SpecialService {
    private static final Object TEST_OBJECT = getObject();

    private static Object getObject(){
        try {
            return Class.forName("Test");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getNumbers(){
        return -1;
    }
}
