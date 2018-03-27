package com.nelson.sign.service.impl;

import com.nelson.sign.Repository.CourseTimeRepository;
import com.nelson.sign.entity.CourseTime;
import com.nelson.sign.service.CourseTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseTimeServiceImpl implements CourseTimeService {

    @Autowired
    private CourseTimeRepository courseTimeRepository;
    @Override
    public CourseTime addCourseTime(CourseTime courseTime) {
        return this.courseTimeRepository.saveAndFlush(courseTime);
    }
}
