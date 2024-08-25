package com.Graduation_Be.mapper;

import com.Graduation_Be.dto.respone.AdvertisementResponseDto;
import com.Graduation_Be.dto.resquest.advertisementDto.AdvertisementCreateRequestDto;
import com.Graduation_Be.dto.resquest.advertisementDto.AdvertisementRequestDto;
import com.Graduation_Be.model.AdvertisementEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AdvertisementMapper {
    AdvertisementEntity toAdvertiserCreateEntity (AdvertisementCreateRequestDto advertisementCreateRequestDto);

    AdvertisementEntity toAdvertiserEntity (AdvertisementRequestDto advertisementRequestDto);

    AdvertisementResponseDto toAdvertiserResponse (AdvertisementEntity advertisementEntity);

}
