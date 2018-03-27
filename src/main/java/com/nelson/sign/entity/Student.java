package com.nelson.sign.entity;

import javax.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue
    @Column(name = "student_id")
    private long studentId;

    @JoinColumn(name = "uid",nullable = false)
    @OneToOne
    private User user;

    @Column(nullable = false)
    private String name;

    private int sex;

    private String phone;

    private String email;

    @JoinColumn(name = "clazz_id",nullable = false)
    @OneToOne
    private Clazz clazz;

    public Student() {
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
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

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }
}
