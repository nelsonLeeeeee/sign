package com.nelson.sign.Repository;

import com.nelson.sign.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student,Long>{

    Student saveAndFlush(Student student);

}
