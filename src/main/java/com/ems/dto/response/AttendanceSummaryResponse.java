package com.ems.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceSummaryResponse {

    private long present;
    private long absent;
    private long onLeave;

    private double attendanceRate;
    private double absentRate;
    private double leaveRate;
}