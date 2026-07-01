package com.ems.repository;

import com.ems.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByDepartmentCode(String departmentCode);

    Optional<Department> findByDepartmentName(String departmentName);

    boolean existsByDepartmentCode(String departmentCode);

    boolean existsByDepartmentName(String departmentName);

    @Query("""
            SELECT d.departmentName, COUNT(e)
            FROM Department d
            LEFT JOIN Employee e ON e.department.id = d.id
            GROUP BY d.departmentName
            """)
    List<Object[]> getDepartmentSummary();

}