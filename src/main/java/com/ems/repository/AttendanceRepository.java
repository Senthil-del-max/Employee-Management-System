package com.ems.repository;

import com.ems.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    long countByEmployeeIdAndStatus(Long employeeId, String status);

    long countByStatus(String status);



}