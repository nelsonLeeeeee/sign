package com.nelson.sign.controller;

import com.nelson.sign.Repository.TeacherRepository;
import com.nelson.sign.entity.Teacher;
import com.nelson.sign.service.TeacherService;
import com.nelson.sign.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/teacher/api")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherRepository teacherRepository;

    @RequestMapping("/addTeacher")
    public Result addTeacher(@Valid Teacher teacher){
        teacher = this.teacherRepository.saveAndFlush(teacher);
        Result result = new Result("OK");
        result.resultMap.put("teacher",teacher);
        return result;
    }

}
