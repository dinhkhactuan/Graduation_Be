package com.Graduation_Be.service.impl;

import com.Graduation_Be.api.ApiResponse;
import com.Graduation_Be.dto.respone.RevenueResponseDto;
import com.Graduation_Be.mapper.RevenueMapper;
import com.Graduation_Be.repository.RevenueRepository;
import com.Graduation_Be.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevenueServiceImpl implements RevenueService {
    @Autowired
    private RevenueRepository revenueRepository;
    @Autowired
    private RevenueMapper revenueMapper;
    @Override
    public List<RevenueResponseDto> getAll() {
        List<RevenueResponseDto> list = revenueMapper.toListRevenueResponse(revenueRepository.findAll());
        return  list;
    }
}
