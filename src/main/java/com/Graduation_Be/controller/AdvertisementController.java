package com.Graduation_Be.controller;

import com.Graduation_Be.api.ApiResponse;
import com.Graduation_Be.dto.respone.AdvertisementResponseDto;
import com.Graduation_Be.dto.resquest.advertisementDto.AdvertisementCreateRequestDto;
import com.Graduation_Be.dto.resquest.advertisementDto.AdvertisementRequestDto;
import com.Graduation_Be.service.impl.AdvertiserServiceImpl;
import com.Graduation_Be.shard.enums.MessageSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/advertisement")
@PreAuthorize("hasRole('admin') or hasRole('advertisement')")
public class AdvertisementController {

    @Autowired
    private AdvertiserServiceImpl advertiserService;

//    get list
    @GetMapping(value = "")
    public ApiResponse<List<AdvertisementResponseDto>> getAll (){
        return new ApiResponse<List<AdvertisementResponseDto>>(200, MessageSys.SUSSCESS ,  advertiserService.getListAdvertiser());
    }

//  tạo mới
    @PostMapping(value = "")
    public ApiResponse<?> addAdvertisment (@RequestBody AdvertisementCreateRequestDto advertisementCreateRequestDto){
        advertiserService.addAdvertiser(advertisementCreateRequestDto);
        return  new ApiResponse<>(200, MessageSys.SUSSCESS , null);
    }

//    Get one
    @GetMapping(value = "/{id}")
    public ApiResponse<Optional<AdvertisementResponseDto>> getOne(@PathVariable Long id){

        return  new ApiResponse<Optional<AdvertisementResponseDto>>(200, MessageSys.SUSSCESS,advertiserService.getOneAdvertiser(id));
    }

//    update
    @PutMapping(value = "")
    public ApiResponse<AdvertisementResponseDto> updateUser (@RequestBody AdvertisementRequestDto advertisementRequestDto){

        return new ApiResponse<>(200, MessageSys.SUSSCESS, advertiserService.updateAdvertiser(advertisementRequestDto));
    }

//    xóa one
    @DeleteMapping(value = "/{id}")
    public ApiResponse<?> deleteOne(@PathVariable Long id){
        advertiserService.deleteAdvertiser(id);
        return  new ApiResponse<>(200, MessageSys.SUSSCESS,null);
    }

    //    xóa all
    @DeleteMapping(value = "/delAll")
    public ApiResponse<?> deleteAll(){
        advertiserService.deleteAllAdvertiser();
        return  new ApiResponse<>(200, MessageSys.SUSSCESS,null);
    }
}
