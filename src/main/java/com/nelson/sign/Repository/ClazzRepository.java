package com.nelson.sign.Repository;

import com.nelson.sign.entity.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClazzRepository extends JpaRepository<Clazz,Long>,PagingAndSortingRepository<Clazz,Long> {

}
