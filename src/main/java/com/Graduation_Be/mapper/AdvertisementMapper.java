package com.Graduation_Be.mapper;

import com.Graduation_Be.dto.respone.AdvertisementResponseDto;
import com.Graduation_Be.dto.respone.AdvertisingFieldResponseDto;
import com.Graduation_Be.dto.respone.RevenueResponseDto;
import com.Graduation_Be.dto.respone.UserResponseDto;
import com.Graduation_Be.dto.resquest.AdvertisingField.AdvertisingFieldResponseDTO;
import com.Graduation_Be.dto.resquest.advertisementDto.AdvertisementCreateRequestDto;
import com.Graduation_Be.dto.resquest.advertisementDto.AdvertisementRequestDto;
import com.Graduation_Be.model.AdvertisementEntity;
import com.Graduation_Be.model.AdvertisingFieldId;
import com.Graduation_Be.model.RevenueEntity;
import com.Graduation_Be.model.UserEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Component
public interface AdvertisementMapper {

    @Mapping(target = "startTime", source = "startDate")
    @Mapping(target = "endTime", source = "endDate")
    @Mapping(target = "advertisingFields", source = "advertisingFields")

    @AfterMapping
    default void afterMapping(@MappingTarget AdvertisementResponseDto dto, AdvertisementEntity entity) {
        if (entity.getStartDate() != null) {
            dto.setStartTime(entity.getStartDate().atStartOfDay());
        }
        if (entity.getEndDate() != null) {
            dto.setEndTime(entity.getEndDate().atTime(LocalTime.MAX));
        }
        if (entity.getAdvertisingFields() != null) {
            dto.setAdvertisingFields(entity.getAdvertisingFields().stream()
                    .map(field -> new AdvertisingFieldResponseDto(field.getAdvertisingFieldId(), field.getAdvertisingFieldName()))
                    .collect(Collectors.toList()));
        }
    }

    AdvertisingFieldResponseDto toAdvertisingFieldResponseDto(AdvertisingFieldId advertisingFieldId);

    AdvertisementEntity toAdvertiserCreateEntity (AdvertisementCreateRequestDto advertisementCreateRequestDto);

    AdvertisementEntity toAdvertiserEntity (AdvertisementRequestDto advertisementRequestDto);


    AdvertisementResponseDto toAdvertiserResponse (AdvertisementEntity advertisementEntity);

    List<AdvertisementResponseDto> toListAdvertiserResponse (List<AdvertisementEntity> advertisementEntities);



    @Mapping(target = "status", ignore = true)
    void updateAdvertiserFromDto(AdvertisementRequestDto dto, @MappingTarget AdvertisementEntity entity);

    RevenueResponseDto toRevenueResponse(RevenueEntity revenueEntity);


    default Optional<AdvertisementResponseDto> toOptionalAdvertisementRespone(Optional<AdvertisementEntity> advertisementEntity) {
        return advertisementEntity.map(this::toAdvertiserResponse);
    }

}
