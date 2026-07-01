package com.ems.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PayrollRequest {

    private Long employeeId;

    private String month;

    private Integer year;

    private BigDecimal basicSalary;

    private BigDecimal hra;

    private BigDecimal transportAllowance;

    private BigDecimal bonus;

    private BigDecimal tax;

    private BigDecimal pf;

    private BigDecimal otherDeduction;

    private String status;

}