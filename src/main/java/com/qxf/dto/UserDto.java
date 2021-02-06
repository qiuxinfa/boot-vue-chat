package com.qxf.dto;

import java.io.Serializable;

/**
 * @ClassName UserDto
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2021/2/5 23:34
 **/
public class UserDto implements Serializable{
    private String username;
    private String email;
    // 用户重置密码的凭证
    private String key;
    // 新密码
    private String password;
    // 确认密码
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
