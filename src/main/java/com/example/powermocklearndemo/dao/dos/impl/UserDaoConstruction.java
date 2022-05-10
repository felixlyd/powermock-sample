package com.example.powermocklearndemo.dao.dos.impl;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/4/26
 */
public class UserDaoConstruction {
    private String username;
    private String password;

    public UserDaoConstruction(String username, String password){
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

    public void insert(){
        throw new UnsupportedOperationException();
    }
}
