package com.ems.service.impl;

import com.ems.dto.request.PayrollRequest;
import com.ems.dto.response.PayrollResponse;
import com.ems.entity.Employee;
import com.ems.entity.Payroll;
import com.ems.repository.EmployeeRepository;
import com.ems.repository.PayrollRepository;
import com.ems.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PayrollServiceImpl implements PayrollService {

    private final PayrollRepository payrollRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public PayrollResponse createPayroll(PayrollRequest request) {

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        BigDecimal grossSalary = request.getBasicSalary()
                .add(request.getHra())
                .add(request.getTransportAllowance())
                .add(request.getBonus());

        BigDecimal totalDeduction = request.getTax()
                .add(request.getPf())
                .add(request.getOtherDeduction());

        BigDecimal netSalary = grossSalary.subtract(totalDeduction);

        Payroll payroll = Payroll.builder()
                .employee(employee)
                .month(request.getMonth())
                .year(request.getYear())
                .basicSalary(request.getBasicSalary())
                .hra(request.getHra())
                .transportAllowance(request.getTransportAllowance())
                .bonus(request.getBonus())
                .tax(request.getTax())
                .pf(request.getPf())
                .otherDeduction(request.getOtherDeduction())
                .grossSalary(grossSalary)
                .totalDeduction(totalDeduction)
                .netSalary(netSalary)
                .status(request.getStatus())
                .build();

        payrollRepository.save(payroll);

        return mapToResponse(payroll);
    }

    @Override
    public PayrollResponse updatePayroll(Long id, PayrollRequest request) {

        Payroll payroll = payrollRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll not found"));

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        BigDecimal grossSalary = request.getBasicSalary()
                .add(request.getHra())
                .add(request.getTransportAllowance())
                .add(request.getBonus());

        BigDecimal totalDeduction = request.getTax()
                .add(request.getPf())
                .add(request.getOtherDeduction());

        BigDecimal netSalary = grossSalary.subtract(totalDeduction);

        payroll.setEmployee(employee);
        payroll.setMonth(request.getMonth());
        payroll.setYear(request.getYear());
        payroll.setBasicSalary(request.getBasicSalary());
        payroll.setHra(request.getHra());
        payroll.setTransportAllowance(request.getTransportAllowance());
        payroll.setBonus(request.getBonus());
        payroll.setTax(request.getTax());
        payroll.setPf(request.getPf());
        payroll.setOtherDeduction(request.getOtherDeduction());
        payroll.setGrossSalary(grossSalary);
        payroll.setTotalDeduction(totalDeduction);
        payroll.setNetSalary(netSalary);
        payroll.setStatus(request.getStatus());

        payrollRepository.save(payroll);

        return mapToResponse(payroll);
    }

    @Override
    public PayrollResponse getPayrollById(Long id) {

        Payroll payroll = payrollRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll not found"));

        return mapToResponse(payroll);
    }

    @Override
    public List<PayrollResponse> getAllPayrolls() {

        return payrollRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    @Override
    public List<PayrollResponse> getPayrollByEmployee(Long employeeId) {

        return payrollRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::mapToResponse)
                .toList();

    }

    @Override
    public void deletePayroll(Long id) {

        payrollRepository.deleteById(id);

    }

    private PayrollResponse mapToResponse(Payroll payroll) {

        return PayrollResponse.builder()
                .id(payroll.getId())
                .employeeId(payroll.getEmployee().getId())
                .employeeName(payroll.getEmployee().getFullName())
                .departmentName(payroll.getEmployee().getDepartment().getDepartmentName())
                .designation(payroll.getEmployee().getDesignation())
                .month(payroll.getMonth())
                .year(payroll.getYear())
                .basicSalary(payroll.getBasicSalary())
                .hra(payroll.getHra())
                .transportAllowance(payroll.getTransportAllowance())
                .bonus(payroll.getBonus())
                .tax(payroll.getTax())
                .pf(payroll.getPf())
                .otherDeduction(payroll.getOtherDeduction())
                .grossSalary(payroll.getGrossSalary())
                .totalDeduction(payroll.getTotalDeduction())
                .netSalary(payroll.getNetSalary())
                .status(payroll.getStatus())
                .build();
    }
}