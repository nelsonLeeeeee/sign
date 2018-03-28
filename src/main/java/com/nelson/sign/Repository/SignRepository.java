package com.nelson.sign.Repository;

import com.nelson.sign.entity.CourseTime;
import com.nelson.sign.entity.Sign;
import com.nelson.sign.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

public interface SignRepository extends JpaRepository<Sign,Long>,JpaSpecificationExecutor<Sign> {


    @Query(value = "select count(*) from sign where student_id = ?1",nativeQuery = true)
    int getCountByStudentId(Long studentId);

    @Query(value = "select count(*) from sign where student_id = ?1 AND course_id = ?2",nativeQuery = true)
    int getCountByStudentIdAndCourseId(Long studentId, Long courseId);

    @Query(value = "select count(*) from sign where student_id = ?1 AND course_id = ?2 AND status = ?3",nativeQuery = true)
    int getCountByStudentIdAndCourseIdAndStatus(Long studentId, Long courseId,int status);


    int countByCourseTime_Course(Long courseId);

    Sign getSignByCourseTimeAndStudent(CourseTime courseTime, Student student);

//    Sign getSignByTeacherIdAndClazzId(Long teacherId, Long clazzId);
}
