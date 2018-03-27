package com.nelson.sign.Repository;

import com.nelson.sign.entity.Sign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SignRepository extends JpaRepository<Sign,Long>,PagingAndSortingRepository<Sign,Long> {


    @Query(value = "select count(*) from sign where student_id = ?1",nativeQuery = true)
    int getCountByStudentId(Long studentId);

    @Query(value = "select count(*) from sign where student_id = ?1 AND course_id = ?2",nativeQuery = true)
    int getCountByStudentIdAndCourseId(Long studentId, Long courseId);

    @Query(value = "select count(*) from sign where student_id = ?1 AND course_id = ?2 AND status = ?3",nativeQuery = true)
    int getCountByStudentIdAndCourseIdAndStatus(Long studentId, Long courseId,int status);


    int countByCourseTime_Course(Long courseId);

//    Sign getSignByTeacherIdAndClazzId(Long teacherId, Long clazzId);
}
