package com.ems.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardStatsResponse {


    private long totalEmployees;

    private long totalDepartments;

    private long presentToday;

    private long pendingLeaves;

}