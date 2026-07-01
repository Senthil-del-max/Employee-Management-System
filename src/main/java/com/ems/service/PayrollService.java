package com.ems.service;

import com.ems.dto.request.PayrollRequest;
import com.ems.dto.response.PayrollResponse;

import java.util.List;

public interface PayrollService {

    PayrollResponse createPayroll(PayrollRequest request);

    PayrollResponse updatePayroll(Long id, PayrollRequest request);

    PayrollResponse getPayrollById(Long id);

    List<PayrollResponse> getAllPayrolls();

    List<PayrollResponse> getPayrollByEmployee(Long employeeId);

    void deletePayroll(Long id);

}