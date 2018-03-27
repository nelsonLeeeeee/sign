package com.nelson.sign.controller;

import com.nelson.sign.entity.CourseTime;
import com.nelson.sign.service.CourseTimeService;
import com.nelson.sign.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/courseTime/api")
public class CourseTimeController {

    @Autowired
    private CourseTimeService courseTimeService;

    @RequestMapping("/addCourseTime")
    public Result addCourseTime(@Valid CourseTime courseTime){
        courseTime = this.courseTimeService.addCourseTime(courseTime);
        Result result = new Result("OK");
        result.resultMap.put("courseTime",courseTime);
        return  result;
    }

}
