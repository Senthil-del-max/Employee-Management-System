package com.ems.service;

import com.ems.dto.request.AttendanceRequest;
import com.ems.dto.response.AttendanceResponse;

import java.util.List;

public interface AttendanceService {

    AttendanceResponse createAttendance(AttendanceRequest request);

    List<AttendanceResponse> getAllAttendance();

    AttendanceResponse getAttendanceById(Long id);

    AttendanceResponse updateAttendance(Long id, AttendanceRequest request);

    void deleteAttendance(Long id);
}