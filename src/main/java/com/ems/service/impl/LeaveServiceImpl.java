package com.ems.service.impl;

import com.ems.dto.request.LeaveRequest;
import com.ems.dto.response.LeaveResponse;
import com.ems.entity.Employee;
import com.ems.entity.Leave;
import com.ems.repository.EmployeeRepository;
import com.ems.repository.LeaveRepository;
import com.ems.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public LeaveResponse createLeave(LeaveRequest request) {

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        int totalDays = (int) ChronoUnit.DAYS.between(
                request.getStartDate(),
                request.getEndDate()
        ) + 1;

        Leave leave = Leave.builder()
                .employee(employee)
                .leaveType(request.getLeaveType())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .totalDays(totalDays)
                .reason(request.getReason())
                .status("Pending")
                .approvedBy("-")
                .build();

        leaveRepository.save(leave);

        return mapToResponse(leave);
    }

    @Override
    public List<LeaveResponse> getAllLeaves() {

        return leaveRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public LeaveResponse getLeaveById(Long id) {

        Leave leave = leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        return mapToResponse(leave);
    }

    @Override
    public LeaveResponse updateLeave(Long id, LeaveRequest request) {

        Leave leave = leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        int totalDays = (int) ChronoUnit.DAYS.between(
                request.getStartDate(),
                request.getEndDate()
        ) + 1;

        leave.setEmployee(employee);
        leave.setLeaveType(request.getLeaveType());
        leave.setStartDate(request.getStartDate());
        leave.setEndDate(request.getEndDate());
        leave.setTotalDays(totalDays);
        leave.setReason(request.getReason());

        leaveRepository.save(leave);

        return mapToResponse(leave);
    }

    @Override
    public void deleteLeave(Long id) {

        leaveRepository.deleteById(id);
    }

    @Override
    public LeaveResponse approveLeave(Long id) {

        Leave leave = leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        leave.setStatus("Approved");
        leave.setApprovedBy("Admin");

        leaveRepository.save(leave);

        return mapToResponse(leave);
    }

    @Override
    public LeaveResponse rejectLeave(Long id) {

        Leave leave = leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        leave.setStatus("Rejected");
        leave.setApprovedBy("Admin");

        leaveRepository.save(leave);

        return mapToResponse(leave);
    }

    private LeaveResponse mapToResponse(Leave leave) {

        return LeaveResponse.builder()
                .id(leave.getId())
                .employeeId(leave.getEmployee().getId())
                .employeeName(leave.getEmployee().getFullName())
                .departmentName(leave.getEmployee().getDepartment().getDepartmentName())
                .leaveType(leave.getLeaveType())
                .startDate(leave.getStartDate())
                .endDate(leave.getEndDate())
                .totalDays(leave.getTotalDays())
                .reason(leave.getReason())
                .status(leave.getStatus())
                .approvedBy(leave.getApprovedBy())
                .createdAt(leave.getCreatedAt())
                .build();
    }

}