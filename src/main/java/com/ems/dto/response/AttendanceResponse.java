package com.ems.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceResponse {

    private Long id;

    private Long employeeId;

    private String employeeName;

    private String departmentName;

    private LocalDate attendanceDate;

    private LocalTime checkIn;

    private LocalTime checkOut;

    private String status;

    private String remarks;

    private LocalDateTime createdAt;
}