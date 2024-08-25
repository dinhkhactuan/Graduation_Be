package com.Graduation_Be.service;


import com.Graduation_Be.dto.respone.AdvertisementResponseDto;
import com.Graduation_Be.dto.resquest.advertisementDto.AdvertisementCreateRequestDto;
import com.Graduation_Be.dto.resquest.advertisementDto.AdvertisementRequestDto;


import java.util.List;

public interface AdvertiserService {

    //getList
    public List<AdvertisementResponseDto> getListAdvertiser();

    //getOne
    public AdvertisementResponseDto getOneAdvertiser();

    //add
    public void addAdvertiser(AdvertisementCreateRequestDto advertisementCreateRequestDto);

    //update

    public void updateAdvertiser(AdvertisementRequestDto advertisementRequestDto);

    //    delete
    public void deleteAdvertiser (Long roleId);
}
