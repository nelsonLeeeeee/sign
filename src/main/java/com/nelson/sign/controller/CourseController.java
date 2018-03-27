package com.nelson.sign.controller;

import com.nelson.sign.entity.Course;
import com.nelson.sign.service.CourseService;
import com.nelson.sign.utils.Result;
import com.nelson.sign.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/course/api")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/addCourse")
    public Result<Course> addCourese(@Valid Course course){
        course = this.courseService.addCourse(course);
        return ResultUtil.success(course);
    }

    /**
     * 学生获取当周的课程信息
     * @param studentId
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping("/getWeekCourse")
    public Result getWeekCourse(@RequestParam("studentId") Long studentId,Long startTime,Long endTime){
        //获取当前周一周日的时间
        //根据studentId获取clazzId
        List<Course> courses = this.courseService.getStudentCourse(studentId,startTime,endTime);
        return ResultUtil.success(courses);
    }



}
