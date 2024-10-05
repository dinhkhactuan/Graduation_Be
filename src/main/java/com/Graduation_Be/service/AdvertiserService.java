package com.Graduation_Be.service;


import com.Graduation_Be.dto.respone.AdvertisementResponseDto;
import com.Graduation_Be.dto.respone.RevenueResponseDto;
import com.Graduation_Be.dto.respone.UserResponseDto;
import com.Graduation_Be.dto.resquest.advertisementDto.AdvertisementCreateRequestDto;
import com.Graduation_Be.dto.resquest.advertisementDto.AdvertisementRequestDto;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AdvertiserService {

    //getList
    public List<AdvertisementResponseDto> getListAdvertiser();

    List<AdvertisementResponseDto> getListAdvertiserByUser(Long userId);

    //
    public AdvertisementResponseDto updateAdvertiserByUser(AdvertisementRequestDto dto);
    public Optional<AdvertisementResponseDto> getOneAdvertiser (Long id);

    //add
    public void addAdvertiser(AdvertisementCreateRequestDto advertisementCreateRequestDto);

    //update

    public AdvertisementResponseDto updateAdvertiser(AdvertisementRequestDto advertisementRequestDto);

    //    delete
    public void deleteAdvertiser (Long roleId);

//    xóa all
    public void deleteAllAdvertiser ();

    public void requestApproval(Long id);

    public void approveAdvertisement(Long id);

    public RevenueResponseDto getAdvertisementRevenue(Long id);

    public BigDecimal getAllRevenue ();
}
