package com.nelson.sign.service.impl;

import com.nelson.sign.Repository.StudentRepository;
import com.nelson.sign.entity.Student;
import com.nelson.sign.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        return this.studentRepository.saveAndFlush(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return this.studentRepository.saveAndFlush(student);
    }

    @Override
    public Page<Student> findStudent(Integer pageIndex, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC,"studentId");
        Pageable pageable = PageRequest.of(pageIndex,pageSize,sort);
        return this.studentRepository.findAll(pageable);
    }
}
