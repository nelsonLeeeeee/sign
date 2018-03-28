package com.nelson.sign.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Sign implements Serializable{


    @Id
    @GeneratedValue
    private Long signId;

    @JoinColumn(name = "student_id",nullable = false)
    @OneToOne
    private Student student;

    @Column(name = "sign_time",nullable = false)
    private Long signTime;

    @Column(nullable = false)
    private String ip;

    @Column(nullable = false)
    private int status;

    @OneToOne
    @JoinColumn(name = "teacher_id",nullable = false)
    private Teacher teacher;

    @JoinColumn(name = "courseTime_id",nullable = false)
    @ManyToOne
    private CourseTime courseTime;


    public Sign() {
    }

    public Long getSignId() {
        return signId;
    }

    public void setSignId(Long signId) {
        this.signId = signId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getSignTime() {
        return signTime;
    }

    public void setSignTime(Long signTime) {
        this.signTime = signTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CourseTime getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(CourseTime courseTime) {
        this.courseTime = courseTime;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
