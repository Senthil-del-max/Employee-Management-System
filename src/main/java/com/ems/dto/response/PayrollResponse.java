package com.ems.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PayrollResponse {

    private Long id;

    private Long employeeId;

    private String employeeName;

    private String departmentName;

    private String designation;

    private String month;

    private Integer year;

    private BigDecimal basicSalary;

    private BigDecimal hra;

    private BigDecimal transportAllowance;

    private BigDecimal bonus;

    private BigDecimal tax;

    private BigDecimal pf;

    private BigDecimal otherDeduction;

    private BigDecimal grossSalary;

    private BigDecimal totalDeduction;

    private BigDecimal netSalary;

    private String status;

}