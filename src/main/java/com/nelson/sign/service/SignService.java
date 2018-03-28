package com.nelson.sign.service;

import com.nelson.sign.entity.Sign;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface SignService {

    /**
     * 用户签到
     * @param sign
     * @return
     */
    Sign sign(Sign sign);

    long checkSignStatus(Long id,Long signTime);

    Map<String, Integer> getLateNumberByStudentAndCourse(Long studentId, Long courseId);

    Sign getSignByTeacherAndClazz(Long teacherId, Long clazzId);

    Page<Sign> getStudentSignStatisticsByCourse(Long courseId, String keyword, Integer pageIndex, Integer pageSize);

//    Page<Sign> findBookCriteria(Integer page, Integer size,  String keyword);
}
