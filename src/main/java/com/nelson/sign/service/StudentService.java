package com.nelson.sign.service;

import com.nelson.sign.entity.Student;
import org.springframework.data.domain.Page;

import javax.validation.Valid;

public interface StudentService {
    Student addStudent(Student student);

    Student updateStudent(Student student);

    Page<Student> findStudent(Integer pageIndex, Integer pageSize);
}
