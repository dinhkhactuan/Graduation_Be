package com.Graduation_Be.controller;

import com.Graduation_Be.api.ApiResponse;
import com.Graduation_Be.dto.respone.AdvertisementResponseDto;
import com.Graduation_Be.mapper.AdvertisementMapper;
import com.Graduation_Be.repository.AdveriserRespository;
import com.Graduation_Be.shard.enums.AdvertisementStatus;
import com.Graduation_Be.shard.enums.MessageSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private AdveriserRespository adveriserRespository;
    @Autowired
    private AdvertisementMapper advertisementMapper;
    @GetMapping("/advertisement-approval")
    public ApiResponse<List<AdvertisementResponseDto>> AdvertismentApproval(){
        List<AdvertisementResponseDto> list = advertisementMapper.toListAdvertiserResponse(adveriserRespository.findByStatus(AdvertisementStatus.APPROVED));
        return new ApiResponse<>(200, MessageSys.SUSSCESS,list);
    }
}
