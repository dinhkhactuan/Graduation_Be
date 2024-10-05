package com.Graduation_Be.controller;

import com.Graduation_Be.api.ApiResponse;
import com.Graduation_Be.service.RevenueService;
import com.Graduation_Be.service.impl.RevenueServiceImpl;
import com.Graduation_Be.shard.enums.MessageSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/revenue")
public class RevenueController {

    @Autowired
    private RevenueServiceImpl revenueService;
    @GetMapping("")
    public ApiResponse<?> getAll(){
        return  new ApiResponse<>(200, MessageSys.SUSSCESS, revenueService.getAll());
    }
}
