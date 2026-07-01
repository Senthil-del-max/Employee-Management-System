package com.ems.controller;

import com.ems.dto.request.PayrollRequest;
import com.ems.dto.response.PayrollResponse;
import com.ems.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payroll")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PayrollController {

    private final PayrollService payrollService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PayrollResponse createPayroll(@RequestBody PayrollRequest request) {
        return payrollService.createPayroll(request);
    }

    @GetMapping
    public List<PayrollResponse> getAllPayrolls() {
        return payrollService.getAllPayrolls();
    }

    @GetMapping("/{id}")
    public PayrollResponse getPayrollById(@PathVariable Long id) {
        return payrollService.getPayrollById(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<PayrollResponse> getPayrollByEmployee(@PathVariable Long employeeId) {
        return payrollService.getPayrollByEmployee(employeeId);
    }

    @PutMapping("/{id}")
    public PayrollResponse updatePayroll(
            @PathVariable Long id,
            @RequestBody PayrollRequest request) {

        return payrollService.updatePayroll(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePayroll(@PathVariable Long id) {
        payrollService.deletePayroll(id);
    }
}