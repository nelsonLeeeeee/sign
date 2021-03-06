package com.nelson.sign.controller;

import com.nelson.sign.entity.Student;
import com.nelson.sign.service.StudentService;
import com.nelson.sign.utils.Result;
import com.nelson.sign.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/student/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/addStudent")
    public Result<Student> addStudent(@Valid Student student){
//,@RequestParam("clazzId") Long clazzId,@RequestParam("uid") Long uid
        student = this.studentService.addStudent(student);
        return ResultUtil.success(student);
    }

    @RequestMapping(value = "/updateStudent")
    public Result updateStudent(@Valid Student student){
        this.studentService.updateStudent(student);
        return  ResultUtil.success();
    }

    @RequestMapping(value = "/findStudent")
    public Result<Page<Student>> findStudent(Integer pageIndex,Integer pageSize){
       Page<Student> studentsPage = this.studentService.findStudent(pageIndex,pageSize);
       return ResultUtil.success(studentsPage);
    }

}
