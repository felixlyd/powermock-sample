package com.example.powermockdemo.note.constants;

/**
 * 错误信息枚举类
 *
 * @author : liuyaodong
 * @date 2022/5/12
 */
public enum ErrorCodeEnum {
    /**
     * 不能连接数据库
     */
    CANNOT_ACCESS_DB(0,"cannot access db"),
    CANNOT_QUERY_DB(1,"cannot query db");

    private final int code;
    private final String value;

    ErrorCodeEnum(int code, String value){
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
