package com.Graduation_Be.mapper;

import com.Graduation_Be.dto.respone.AdvertisementResponseDto;
import com.Graduation_Be.dto.respone.UserResponseDto;
import com.Graduation_Be.dto.resquest.advertisementDto.AdvertisementCreateRequestDto;
import com.Graduation_Be.dto.resquest.advertisementDto.AdvertisementRequestDto;
import com.Graduation_Be.model.AdvertisementEntity;
import com.Graduation_Be.model.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
@Component
public interface AdvertisementMapper {
    AdvertisementEntity toAdvertiserCreateEntity (AdvertisementCreateRequestDto advertisementCreateRequestDto);

    AdvertisementEntity toAdvertiserEntity (AdvertisementRequestDto advertisementRequestDto);


    AdvertisementResponseDto toAdvertiserResponse (AdvertisementEntity advertisementEntity);

    List<AdvertisementResponseDto> toListAdvertiserResponse (List<AdvertisementEntity> advertisementEntities);

    default Optional<AdvertisementResponseDto> toOptionalAdvertisementRespone(Optional<AdvertisementEntity> advertisementEntity) {
        return advertisementEntity.map(this::toAdvertiserResponse);
    }

}
