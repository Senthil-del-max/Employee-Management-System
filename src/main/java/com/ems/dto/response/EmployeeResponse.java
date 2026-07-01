package com.ems.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponse {

    private Long id;

    private String employeeId;

    private String fullName;

    private String email;

    private String phone;

    private String designation;

    private Double salary;

    private LocalDate joiningDate;

    private String departmentName;

    private boolean active;

    private LocalDateTime createdAt;
}