package com.ems.service;

import com.ems.dto.response.DashboardStatsResponse;
import com.ems.dto.response.DepartmentSummaryResponse;
import com.ems.dto.response.EmployeeDashboardResponse;
import com.ems.dto.response.HeadcountTrendResponse;
import com.ems.dto.response.AttendanceSummaryResponse;
import com.ems.dto.response.ActivityResponse;

import java.util.List;

public interface DashboardService {

    DashboardStatsResponse getDashboardStats();

    EmployeeDashboardResponse getEmployeeDashboard(String email);

    List<DepartmentSummaryResponse> getDepartmentSummary();

    HeadcountTrendResponse getHeadcountTrend();

    AttendanceSummaryResponse getAttendanceSummary();

    List<ActivityResponse> getRecentActivities();

    List<ActivityResponse> getNotifications();

    List<ActivityResponse> getUpcomingEvents();

}