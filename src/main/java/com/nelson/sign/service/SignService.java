package com.nelson.sign.service;

import com.nelson.sign.entity.Sign;

import java.util.Map;

public interface SignService {

    /**
     * 用户签到
     * @param sign
     * @return
     */
    Sign sign(Sign sign);

    long checkSignStatus(Long id,Long signTime);

    int getLateNumber(Long studentId,Long courseId);

    Map<String, Integer> getLateNumberByStudentAndCourse(Long studentId, Long courseId);

    Sign getSignByTeacherAndClazz(Long teacherId, Long clazzId);

    int getLateNumberByCourse(Long courseId);
}
