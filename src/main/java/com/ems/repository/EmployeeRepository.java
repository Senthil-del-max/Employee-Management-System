package com.ems.repository;

import com.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByEmail(String email);

    boolean existsByEmployeeId(String employeeId);

    Optional<Employee> findByEmail(String email);

    List<Employee> findTop5ByOrderByCreatedAtDesc();

    long countByJoiningDateBetween(LocalDate start, LocalDate end);

    long countByActiveFalse();



}