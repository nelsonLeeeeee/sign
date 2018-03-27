package com.nelson.sign.entity;

import javax.persistence.*;

@Entity
public class Teacher {
    @Id
    @GeneratedValue
    @Column(name = "teacher_id")
    private long teacherId;

    @JoinColumn(name = "uid",nullable = false)
    @OneToOne
    private User user;

    @Column(nullable = false)
    private String name;

    private int sex;

    private String phone;

    private String email;

    public Teacher() {
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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
}
