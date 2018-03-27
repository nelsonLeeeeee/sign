package com.nelson.sign.Repository;

import com.nelson.sign.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CourseRepository extends JpaRepository<Course,Long>,PagingAndSortingRepository<Course,Long> {

    Course getCourseByCourseId(Long courseId);

//    @Query(value = "",nativeQuery = true)
//    List<Course> getCourseByClazzIdaAndStartTimeBeforeAndEndTimeAfter(Long clazzId,Long startTime,Long endTime);

}
