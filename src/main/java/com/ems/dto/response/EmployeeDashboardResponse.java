package com.ems.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDashboardResponse {

    private Long employeeId;
    private String employeeName;
    private String department;
    private String designation;

    private Double attendancePercentage;

    private Integer leaveBalance;

    private Long pendingLeaves;

    private Long approvedLeaves;

    private BigDecimal latestSalary;

}