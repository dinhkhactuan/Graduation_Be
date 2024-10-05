package com.Graduation_Be.service;

import com.Graduation_Be.api.ApiResponse;
import com.Graduation_Be.dto.respone.RevenueResponseDto;

import java.util.List;

public interface RevenueService {

    public List<RevenueResponseDto> getAll();
}
