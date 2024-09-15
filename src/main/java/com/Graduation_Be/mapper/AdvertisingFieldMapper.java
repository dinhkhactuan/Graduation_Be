package com.Graduation_Be.mapper;

import com.Graduation_Be.dto.resquest.AdvertisingField.AdvertisingFieldRequestDTO;
import com.Graduation_Be.dto.resquest.AdvertisingField.AdvertisingFieldResponseDTO;
import com.Graduation_Be.dto.resquest.roleDto.RoleRequestDto;
import com.Graduation_Be.model.AdvertisingFieldId;
import com.Graduation_Be.model.RoleEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface AdvertisingFieldMapper {
    AdvertisingFieldId toAdvertisingFieldId (AdvertisingFieldRequestDTO requestDTO);
    AdvertisingFieldResponseDTO toAdvertisingFieldIdResponseDTO (AdvertisingFieldId advertisingFieldId);

    List<AdvertisingFieldResponseDTO> toListAdvertisingFieldIdResponseDTO (List<AdvertisingFieldId> advertisingFieldId);

}
