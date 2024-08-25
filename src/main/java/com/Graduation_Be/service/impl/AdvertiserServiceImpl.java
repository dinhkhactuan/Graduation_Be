package com.Graduation_Be.service.impl;

import com.Graduation_Be.dto.respone.AdvertisementResponseDto;
import com.Graduation_Be.dto.resquest.advertisementDto.AdvertisementCreateRequestDto;
import com.Graduation_Be.dto.resquest.advertisementDto.AdvertisementRequestDto;
import com.Graduation_Be.service.AdvertiserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdvertiserServiceImpl implements AdvertiserService {
    @Override
    public List<AdvertisementResponseDto> getListAdvertiser() {
        return null;
    }

    @Override
    public AdvertisementResponseDto getOneAdvertiser() {
        return null;
    }

    @Override
    public void addAdvertiser(AdvertisementCreateRequestDto advertisementCreateRequestDto) {

    }

    @Override
    public void updateAdvertiser(AdvertisementRequestDto advertisementRequestDto) {

    }

    @Override
    public void deleteAdvertiser(Long roleId) {

    }
}
