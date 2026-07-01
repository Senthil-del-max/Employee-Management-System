package com.ems.repository;

import com.ems.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Long> {

    long countByEmployeeIdAndStatus(Long employeeId, String status);

    long countByStatus(String status);

    List<Leave> findTop5ByStatusOrderByStartDateAsc(String status);

}