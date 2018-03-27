package com.nelson.sign.entity;

import javax.persistence.*;

@Entity
public class Course {

    @Id
    @GeneratedValue
    @Column(name = "course_id")
    private Long courseId;

    @Column(nullable = false)
    private String name;

    @JoinColumn(name = "teacher_id",nullable = false)
    @OneToOne
    private Teacher teacher;

    @OneToOne
    @JoinColumn(name = "clazz_id",nullable = false)
    private Clazz clazz;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }
}
