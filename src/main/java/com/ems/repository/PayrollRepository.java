package com.ems.repository;

import com.ems.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    List<Payroll> findByEmployeeId(Long employeeId);

    Payroll findTopByEmployeeIdOrderByYearDesc(Long employeeId);

}