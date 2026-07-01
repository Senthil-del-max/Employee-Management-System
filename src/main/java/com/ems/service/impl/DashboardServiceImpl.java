package com.ems.service.impl;


import com.ems.dto.response.DashboardStatsResponse;
import com.ems.dto.response.EmployeeDashboardResponse;
import com.ems.entity.Employee;
import com.ems.entity.Payroll;
import com.ems.entity.Role;
import com.ems.repository.AttendanceRepository;
import com.ems.repository.EmployeeRepository;
import com.ems.repository.LeaveRepository;
import com.ems.repository.PayrollRepository;
import com.ems.repository.UserRepository;
import com.ems.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ems.repository.DepartmentRepository;
import com.ems.dto.response.DepartmentSummaryResponse;
import java.util.List;
import com.ems.dto.response.HeadcountTrendResponse;
import java.time.LocalDate;
import java.math.BigDecimal;
import com.ems.dto.response.AttendanceSummaryResponse;
import java.util.ArrayList;
import java.util.List;
import com.ems.dto.response.ActivityResponse;


@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;
    private final LeaveRepository leaveRepository;
    private final PayrollRepository payrollRepository;


    private final DepartmentRepository departmentRepository;





    @Override
    public DashboardStatsResponse getDashboardStats() {

        return DashboardStatsResponse.builder()

                .totalEmployees(employeeRepository.count())

                .totalDepartments(departmentRepository.count())

                .presentToday(
                        attendanceRepository.countByStatus("Present")
                )

                .pendingLeaves(
                        leaveRepository.countByStatus("Pending")
                )

                .build();
    }

    @Override
    public EmployeeDashboardResponse getEmployeeDashboard(String email) {

        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Long employeeId = employee.getId();

        long presentDays = attendanceRepository
                .countByEmployeeIdAndStatus(employeeId, "Present");

        long absentDays = attendanceRepository
                .countByEmployeeIdAndStatus(employeeId, "Absent");

        long pendingLeaves = leaveRepository
                .countByEmployeeIdAndStatus(employeeId, "Pending");

        long approvedLeaves = leaveRepository
                .countByEmployeeIdAndStatus(employeeId, "Approved");

        Payroll payroll = payrollRepository
                .findTopByEmployeeIdOrderByYearDesc(employeeId);

        double attendancePercentage = 0;

        long totalAttendance = presentDays + absentDays;

        if (totalAttendance > 0) {
            attendancePercentage = (presentDays * 100.0) / totalAttendance;
        }

        return EmployeeDashboardResponse.builder()
                .employeeId(employee.getId())
                .employeeName(employee.getFullName())
                .department(employee.getDepartment().getDepartmentName())
                .designation(employee.getDesignation())
                .attendancePercentage(attendancePercentage)
                .leaveBalance(10) // Temporary
                .pendingLeaves(pendingLeaves)
                .approvedLeaves(approvedLeaves)
                .latestSalary(
                        payroll != null
                                ? payroll.getNetSalary()
                                : BigDecimal.ZERO
                )
                .build();
    }

    @Override
    public List<DepartmentSummaryResponse> getDepartmentSummary() {

        return departmentRepository.getDepartmentSummary()
                .stream()
                .map(obj -> DepartmentSummaryResponse.builder()
                        .departmentName((String) obj[0])
                        .totalEmployees((Long) obj[1])
                        .build())
                .toList();
    }

    @Override
    public HeadcountTrendResponse getHeadcountTrend() {

        LocalDate start = LocalDate.now().withDayOfYear(1);

        long hires = employeeRepository.countByJoiningDateBetween(
                start,
                LocalDate.now()
        );

        long inactive = employeeRepository.countByActiveFalse();

        return HeadcountTrendResponse.builder()
                .totalHires(hires)
                .totalInactive(inactive)
                .netGrowth(hires - inactive)
                .build();
    }

    @Override
    public AttendanceSummaryResponse getAttendanceSummary() {

        long present = attendanceRepository.countByStatus("Present");
        long absent = attendanceRepository.countByStatus("Absent");
        long onLeave = leaveRepository.countByStatus("Approved");

        long total = present + absent + onLeave;

        double attendanceRate = 0;
        double absentRate = 0;
        double leaveRate = 0;

        if (total > 0) {
            attendanceRate = (present * 100.0) / total;
            absentRate = (absent * 100.0) / total;
            leaveRate = (onLeave * 100.0) / total;
        }

        return AttendanceSummaryResponse.builder()
                .present(present)
                .absent(absent)
                .onLeave(onLeave)
                .attendanceRate(attendanceRate)
                .absentRate(absentRate)
                .leaveRate(leaveRate)
                .build();
    }

    @Override
    public List<ActivityResponse> getRecentActivities() {

        List<ActivityResponse> activities = new ArrayList<>();

        employeeRepository.findTop5ByOrderByCreatedAtDesc()
                .forEach(emp -> activities.add(
                        ActivityResponse.builder()
                                .title("New Employee")
                                .description(emp.getFullName() + " joined " +
                                        emp.getDepartment().getDepartmentName())
                                .time(emp.getCreatedAt().toLocalDate().toString())
                                .build()
                ));

        return activities;
    }

    @Override
    public List<ActivityResponse> getNotifications() {

        List<ActivityResponse> notifications = new ArrayList<>();

        long pendingLeaves = leaveRepository.countByStatus("Pending");

        notifications.add(ActivityResponse.builder()
                .title("Pending Leave Requests")
                .description(pendingLeaves + " leave request(s) waiting for approval")
                .time("Today")
                .build());

        long absent = attendanceRepository.countByStatus("Absent");

        notifications.add(ActivityResponse.builder()
                .title("Attendance Alert")
                .description(absent + " employee(s) absent today")
                .time("Today")
                .build());

        return notifications;
    }

    @Override
    public List<ActivityResponse> getUpcomingEvents() {

        List<ActivityResponse> events = new ArrayList<>();

        leaveRepository.findTop5ByStatusOrderByStartDateAsc("Approved")
                .forEach(l -> events.add(
                        ActivityResponse.builder()
                                .title("Approved Leave")
                                .description(l.getEmployee().getFullName())
                                .time(l.getStartDate().toString())
                                .build()
                ));

        return events;
    }

}