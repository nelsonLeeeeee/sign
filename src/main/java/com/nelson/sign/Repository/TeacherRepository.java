package com.nelson.sign.Repository;

import com.nelson.sign.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long>,PagingAndSortingRepository<Teacher,Long> {
}
