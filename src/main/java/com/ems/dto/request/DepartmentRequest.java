package com.ems.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentRequest {

    @NotBlank(message = "Department Name is required")
    private String departmentName;

    @NotBlank(message = "Department Code is required")
    private String departmentCode;

    private String description;

}