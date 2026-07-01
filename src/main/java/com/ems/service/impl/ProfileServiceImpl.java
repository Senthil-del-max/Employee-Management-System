package com.ems.service.impl;

import com.ems.dto.response.ProfileResponse;
import com.ems.entity.Employee;
import com.ems.entity.User;
import com.ems.repository.EmployeeRepository;
import com.ems.repository.UserRepository;
import com.ems.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    @Override
    public ProfileResponse getProfile(String email) {

        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ProfileResponse.builder()
                .employeeId(employee.getEmployeeId())
                .fullName(employee.getFullName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .designation(employee.getDesignation())
                .department(employee.getDepartment().getDepartmentName())
                .joiningDate(employee.getJoiningDate())
                .role(user.getRole().name())
                .active(employee.isActive())
                .build();

    }

}