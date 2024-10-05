package com.Graduation_Be.service.impl;

import com.Graduation_Be.dto.respone.ApprovalRequestResponseDto;
import com.Graduation_Be.exception.exceptionOption.ResourceNotFoundException;
import com.Graduation_Be.mapper.ApprovalRequestMapper;
import com.Graduation_Be.model.ApprovalRequestEntity;
import com.Graduation_Be.repository.ApprovalRequestRepository;
import com.Graduation_Be.service.ApprovalRequestService;
import com.Graduation_Be.shard.enums.ApprovalStatus;
import com.Graduation_Be.shard.enums.MessageSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprovalRequestServiceImpl implements ApprovalRequestService {

    @Autowired
    private ApprovalRequestRepository approvalRequestRepository;

    @Autowired
    private ApprovalRequestMapper approvalRequestMapper;

    @Override
    public List<ApprovalRequestResponseDto> getRecentRequests() {
        List<ApprovalRequestEntity> entities = approvalRequestRepository.findByStatus(ApprovalStatus.PENDING);
        return approvalRequestMapper.To_Approval_Response(entities);
    }

    @Override
    public void deleteApprovalRequestById(Long id) {
        if(approvalRequestRepository.existsById(id)){
             new ResourceNotFoundException(MessageSys.ID_NOT_EXIST);
             return;
        }
        approvalRequestRepository.deleteById(id);
    }
}
