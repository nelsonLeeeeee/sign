package com.nelson.sign.service.impl;

import com.nelson.sign.entity.Course;
import com.nelson.sign.service.CourseService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class CourseServiceImplTest {

    @Autowired
    private CourseService courseService;

    @Test
    public void addCourseServiceTest(){
        Course course = new Course();
        course.setName("数据结构（软件工程一班）");
        course.setTeacher(null);
    }

}