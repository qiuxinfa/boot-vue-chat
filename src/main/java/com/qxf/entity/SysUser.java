package com.qxf.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author qiuxinfa
 * @since 2021-01-08
 */
public class SysUser implements UserDetails,Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别，1男2女
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户状态，1正常2冻结
     */
    private Integer status;

    /**
     * 头像uri
     */
    private String avatar;

    @TableField(exist = false)
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    // 账号没有过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账号没有被锁定
    @Override
    public boolean isAccountNonLocked() {
        return status == 1;
    }

    // 密码没有过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 是否可用
    @Override
    public boolean isEnabled() {
        return status == 1;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // 权限集合
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "SysUser{" +
            "id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", sex=" + sex +
            ", age=" + age +
            ", phone=" + phone +
            ", email=" + email +
            ", status=" + status +
            ", avatar=" + avatar +
        "}";
    }
}
