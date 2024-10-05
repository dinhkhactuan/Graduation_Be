package com.Graduation_Be.mapper;

import com.Graduation_Be.dto.respone.RevenueResponseDto;
import com.Graduation_Be.dto.respone.RoleResponseDto;
import com.Graduation_Be.model.RevenueEntity;
import com.Graduation_Be.model.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface RevenueMapper {
    @Mapping(target = "advertisementId", source = "advertisement.advertisementId")
    RevenueResponseDto toRevenueResponse (RevenueEntity revenueEntity);

    List<RevenueResponseDto> toListRevenueResponse (List<RevenueEntity> revenueEntity);

}
