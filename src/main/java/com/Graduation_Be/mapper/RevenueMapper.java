package com.Graduation_Be.mapper;

import com.Graduation_Be.dto.respone.RevenueResponseDto;
import com.Graduation_Be.dto.respone.RoleResponseDto;
import com.Graduation_Be.model.RevenueEntity;
import com.Graduation_Be.model.RoleEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface RevenueMapper {
    RevenueResponseDto toRevenueResponse (RevenueEntity revenueEntity);

}
