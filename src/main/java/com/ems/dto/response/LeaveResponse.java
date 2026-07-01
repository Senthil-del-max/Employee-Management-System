package com.ems.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveResponse {

    private Long id;

    private Long employeeId;

    private String employeeName;

    private String departmentName;

    private String leaveType;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer totalDays;

    private String reason;

    private String status;

    private String approvedBy;

    private LocalDateTime createdAt;
}