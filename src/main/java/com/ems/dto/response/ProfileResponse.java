package com.ems.dto.response;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {

    private String employeeId;
    private String fullName;
    private String email;
    private String phone;
    private String designation;
    private String department;
    private LocalDate joiningDate;
    private String role;
    private boolean active;

}