package com.nelson.sign.service;

import com.nelson.sign.entity.Course;

import java.util.List;

public interface CourseService {
    Course getCourseByCourseId(Long courseId);

    Course addCourse(Course course);

    List<Course> getStudentCourse(Long clazzId,Long startTime,Long endTime);



}
