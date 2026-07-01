package com.ems.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceRequest {

    @NotNull(message = "Employee is required")
    private Long employeeId;

    @NotNull(message = "Attendance Date is required")
    private LocalDate attendanceDate;

    private LocalTime checkIn;

    private LocalTime checkOut;

    @NotBlank(message = "Status is required")
    private String status;

    private String remarks;
}