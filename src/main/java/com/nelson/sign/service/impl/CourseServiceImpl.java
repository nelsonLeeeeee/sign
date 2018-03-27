package com.nelson.sign.service.impl;

import com.nelson.sign.Repository.CourseRepository;
import com.nelson.sign.Repository.CourseTimeRepository;
import com.nelson.sign.Repository.StudentRepository;
import com.nelson.sign.entity.Clazz;
import com.nelson.sign.entity.Course;
import com.nelson.sign.entity.Student;
import com.nelson.sign.service.CourseService;
import com.nelson.sign.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseTimeRepository courseTimeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Course getCourseByCourseId(Long courseId) {
        Course course = this.courseRepository.getCourseByCourseId(courseId);
        if (course == null) {
            return null;
        }
        return course;
    }

    @Override
    public Course addCourse(Course course) {
        return this.courseRepository.saveAndFlush(course);
    }

    @Override
    public List<Course> getStudentCourse(Long studenId, Long startTime, Long endTime) {
        Student student = this.studentRepository.getOne(studenId);
        if (student == null) {
            return null;
        }
        Clazz clazz = student.getClazz();
        return this.courseTimeRepository.getCourseByClazz_clszzIdAndStartTimeBeforeAndEndTimeAfter(clazz.getClszzId(),startTime, endTime);
    }
}
