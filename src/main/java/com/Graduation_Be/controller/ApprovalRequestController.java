package com.Graduation_Be.controller;

import com.Graduation_Be.api.ApiResponse;
import com.Graduation_Be.dto.respone.ApprovalRequestResponseDto;
import com.Graduation_Be.mapper.ApprovalRequestMapper;
import com.Graduation_Be.model.ApprovalRequestEntity;
import com.Graduation_Be.service.impl.ApprovalRequestServiceImpl;
import com.Graduation_Be.shard.enums.MessageSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/approval-requests")
public class ApprovalRequestController {

    @Autowired
    private ApprovalRequestServiceImpl requestService;



    @GetMapping(value = "")
    public ApiResponse<List<ApprovalRequestResponseDto>> GetAll(){
        return new ApiResponse<>(200, MessageSys.SUSSCESS, requestService.getRecentRequests());
    }

    @DeleteMapping(value = "/{id}")
    public void DeleteById(@PathVariable Long id){
         requestService.deleteApprovalRequestById(id);
    }
}
