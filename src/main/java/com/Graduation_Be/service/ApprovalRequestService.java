package com.Graduation_Be.service;

import com.Graduation_Be.dto.respone.ApprovalRequestResponseDto;
import com.Graduation_Be.model.ApprovalRequestEntity;

import java.util.List;

public interface ApprovalRequestService {
    public List<ApprovalRequestResponseDto> getRecentRequests();

    public void deleteApprovalRequestById(Long id);
}
