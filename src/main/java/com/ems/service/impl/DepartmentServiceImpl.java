package com.ems.service.impl;

import com.ems.dto.request.DepartmentRequest;
import com.ems.dto.response.DepartmentResponse;
import com.ems.entity.Department;
import com.ems.repository.DepartmentRepository;
import com.ems.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ems.dto.response.AttendanceSummaryResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest request) {

        if (departmentRepository.existsByDepartmentCode(request.getDepartmentCode())) {
            throw new RuntimeException("Department Code already exists");
        }

        if (departmentRepository.existsByDepartmentName(request.getDepartmentName())) {
            throw new RuntimeException("Department Name already exists");
        }

        Department department = Department.builder()
                .departmentName(request.getDepartmentName())
                .departmentCode(request.getDepartmentCode())
                .description(request.getDescription())
                .active(true)
                .build();

        Department savedDepartment = departmentRepository.save(department);

        return DepartmentResponse.builder()
                .id(savedDepartment.getId())
                .departmentName(savedDepartment.getDepartmentName())
                .departmentCode(savedDepartment.getDepartmentCode())
                .description(savedDepartment.getDescription())
                .active(savedDepartment.isActive())
                .build();
    }

    @Override
    public List<DepartmentResponse> getAllDepartments() {

        return departmentRepository.findAll()
                .stream()
                .map(department -> DepartmentResponse.builder()
                        .id(department.getId())
                        .departmentName(department.getDepartmentName())
                        .departmentCode(department.getDepartmentCode())
                        .description(department.getDescription())
                        .active(department.isActive())
                        .build())
                .toList();

    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        return DepartmentResponse.builder()
                .id(department.getId())
                .departmentName(department.getDepartmentName())
                .departmentCode(department.getDepartmentCode())
                .description(department.getDescription())
                .active(department.isActive())
                .build();

    }

    @Override
    public DepartmentResponse updateDepartment(Long id, DepartmentRequest request) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        department.setDepartmentName(request.getDepartmentName());
        department.setDepartmentCode(request.getDepartmentCode());
        department.setDescription(request.getDescription());

        Department updatedDepartment = departmentRepository.save(department);

        return DepartmentResponse.builder()
                .id(updatedDepartment.getId())
                .departmentName(updatedDepartment.getDepartmentName())
                .departmentCode(updatedDepartment.getDepartmentCode())
                .description(updatedDepartment.getDescription())
                .active(updatedDepartment.isActive())
                .build();

    }
    @Override
    public void deleteDepartment(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        departmentRepository.delete(department);

    }
}