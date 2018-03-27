package com.nelson.sign.entity;

import javax.persistence.*;

@Entity
@Table(name = "`leave`")
public class Leave {
    @Id
    @GeneratedValue
    @Column(name = "leave_id")
    private Long leaveId;

    @JoinColumn(name = "student_id")
    @OneToOne
    private Student student;

    private String descpt;

    @Column(name = "start_time")
    private long startTime;

    @Column(name = "end_time")
    private long endTime;

    @OneToOne
    @JoinColumn(name = "approval_id")
    private Teacher teacher;

    private int result;

    public Leave() {
    }

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public String getDescpt() {
        return descpt;
    }

    public void setDescpt(String descpt) {
        this.descpt = descpt;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
