package com.ems.service;

import com.ems.dto.request.LeaveRequest;
import com.ems.dto.response.LeaveResponse;

import java.util.List;

public interface LeaveService {

    LeaveResponse createLeave(LeaveRequest request);

    List<LeaveResponse> getAllLeaves();

    LeaveResponse getLeaveById(Long id);

    LeaveResponse updateLeave(Long id, LeaveRequest request);

    void deleteLeave(Long id);

    LeaveResponse approveLeave(Long id);

    LeaveResponse rejectLeave(Long id);

}