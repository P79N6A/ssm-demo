package com.fc.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fangcong on 2016/11/30.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 4309405241345366002L;


    private Long id;
    private String realName;
    private Character sex;
    private Integer age;
    private String loginName;
    private String loginPassword;
    private Date joinDate;

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public User() {

    }

    public User(String realName, Character sex, Integer age) {
        this.realName = realName;
        this.sex = sex;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", realName='" + realName + '\'' +
            ", sex=" + sex +
            ", age=" + age +
            ", loginName='" + loginName + '\'' +
            ", loginPassword='" + loginPassword + '\'' +
            ", joinDate=" + joinDate +
            '}';
    }
}
