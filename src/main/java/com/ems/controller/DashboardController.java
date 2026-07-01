package com.ems.controller;

import com.ems.dto.response.DashboardStatsResponse;
import com.ems.dto.response.EmployeeDashboardResponse;
import com.ems.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.ems.dto.response.DepartmentSummaryResponse;
import java.util.List;
import com.ems.dto.response.HeadcountTrendResponse;
import com.ems.dto.response.AttendanceSummaryResponse;
import com.ems.dto.response.ActivityResponse;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/stats")
    public DashboardStatsResponse getDashboardStats() {
        return dashboardService.getDashboardStats();
    }

    @GetMapping("/employee")
    public EmployeeDashboardResponse getEmployeeDashboard(
            @RequestParam String email) {

        return dashboardService.getEmployeeDashboard(email);
    }

    @GetMapping("/department-summary")
    public List<DepartmentSummaryResponse> getDepartmentSummary() {
        return dashboardService.getDepartmentSummary();
    }

    @GetMapping("/headcount")
    public HeadcountTrendResponse getHeadcountTrend() {

        return dashboardService.getHeadcountTrend();

    }

    @GetMapping("/attendance-summary")
    public AttendanceSummaryResponse getAttendanceSummary() {
        return dashboardService.getAttendanceSummary();
    }

    @GetMapping("/activities")
    public List<ActivityResponse> getRecentActivities() {
        return dashboardService.getRecentActivities();
    }

    @GetMapping("/notifications")
    public List<ActivityResponse> getNotifications() {
        return dashboardService.getNotifications();
    }

    @GetMapping("/events")
    public List<ActivityResponse> getUpcomingEvents() {
        return dashboardService.getUpcomingEvents();
    }
}