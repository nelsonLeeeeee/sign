package com.nelson.sign.entity;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long uid;

    @Column(name = "name", unique = true,nullable = false )
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(name = "last_login_time")
    private Long lastLoginTime;

    @Column(name = "isused",columnDefinition = "tinyint default 1")
    private int isused;

    public User() {
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getIsused() {
        return isused;
    }

    public void setIsused(int isused) {
        this.isused = isused;
    }
}
