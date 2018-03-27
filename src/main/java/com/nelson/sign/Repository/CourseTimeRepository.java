package com.nelson.sign.Repository;

import com.nelson.sign.entity.Course;
import com.nelson.sign.entity.CourseTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CourseTimeRepository extends JpaRepository<CourseTime,Long>,PagingAndSortingRepository<CourseTime,Long> {
    List<Course> getCourseByClazz_clszzIdAndStartTimeBeforeAndEndTimeAfter(Long clazzId, Long startTime, Long endTime);
}
