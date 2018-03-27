package com.nelson.sign.Repository;

import com.nelson.sign.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LeaveRepository extends JpaRepository<Leave,Long>,PagingAndSortingRepository<Leave,Long> {
}
