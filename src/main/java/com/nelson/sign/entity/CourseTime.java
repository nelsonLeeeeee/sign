package com.nelson.sign.entity;

import javax.persistence.*;

@Entity
public class CourseTime {

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(name = "course_id",nullable = false)
    @OneToOne
    private Course course;

    @Column(name = "start_time",nullable = false)
    private Long startTime;

    @Column(name = "end_time",nullable = false)
    private Long endTime;

    @OneToOne
    @JoinColumn(name = "clazz_id",nullable = false)
    private Clazz clazz;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }


}
