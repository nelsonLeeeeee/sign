package com.nelson.sign.service.impl;

import com.nelson.sign.Repository.CourseTimeRepository;
import com.nelson.sign.Repository.SignRepository;
import com.nelson.sign.config.SignConfig;
import com.nelson.sign.entity.CourseTime;
import com.nelson.sign.entity.Sign;
import com.nelson.sign.service.CourseService;
import com.nelson.sign.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class SignServiceImpl  implements SignService{

    @Autowired
    private SignRepository signRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseTimeRepository courseTimeRepository;

    @Override
    public Sign sign(Sign sign) {
        return this.signRepository.saveAndFlush(sign);
    }

    @Override
    public long checkSignStatus(Long id,Long signTime) {
        CourseTime courseTime =  this.courseTimeRepository.getOne(id);
        if(courseTime==null){
            throw new IllegalArgumentException("ERR_COURSE_NOT_FOUND");
        }
        long result = courseTime.getStartTime() - signTime;
        return result;
    }

    @Override
    public int getLateNumber(Long studentId,Long courseId) {
        return this.signRepository.getCountByStudentId(studentId);
    }

    @Override
    public Map<String, Integer> getLateNumberByStudentAndCourse(Long studentId, Long courseId) {
        int lateNumber = this.signRepository.getCountByStudentIdAndCourseIdAndStatus(studentId,courseId, SignConfig.STATUS_LATE);
        int absentNumber = this.signRepository.getCountByStudentIdAndCourseIdAndStatus(studentId,courseId,SignConfig.STATUS_ABSENT);
        int replaceNumber = this.signRepository.getCountByStudentIdAndCourseIdAndStatus(studentId,courseId,SignConfig.STATUS_REPLACE);
        int leaveNumber = this.signRepository.getCountByStudentIdAndCourseIdAndStatus(studentId,courseId,SignConfig.STATUS_LEAVE);
        Map<String,Integer> resultMap = new HashMap<String,Integer>();
        resultMap.put(SignConfig.LATE,SignConfig.STATUS_LATE);
        resultMap.put(SignConfig.ABSENT,SignConfig.STATUS_ABSENT);
        resultMap.put(SignConfig.REPLACE,SignConfig.STATUS_REPLACE);
        resultMap.put(SignConfig.LEAVE,SignConfig.STATUS_LEAVE);
        return resultMap;
    }

    @Override
    public Sign getSignByTeacherAndClazz(Long teacherId, Long clazzId) {
        return null;
//        return this.signRepository.getSignByTeacherIdAndClazzId(teacherId, clazzId);
    }

    @Override
    public int getLateNumberByCourse(Long courseId) {
        return this.signRepository.countByCourseTime_Course(courseId);
    }

}
