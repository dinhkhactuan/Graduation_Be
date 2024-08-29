package com.Graduation_Be.mapper;

import com.Graduation_Be.dto.respone.RoleResponseDto;
import com.Graduation_Be.dto.resquest.roleDto.RoleCreateRequestDto;
import com.Graduation_Be.dto.resquest.roleDto.RoleRequestDto;
import com.Graduation_Be.model.RoleEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
@Component
public interface RoleMapper {
    RoleEntity toRoleEntity (RoleRequestDto roleRequestDto);

    RoleEntity toRoleCreateEntity (RoleCreateRequestDto roleCreateRequestDto);

    RoleResponseDto toRoleResponse (RoleEntity roleEntity);

    List<RoleResponseDto> toListRoleResponse (List<RoleEntity> roleEntity);

    default Optional<RoleResponseDto> toOptinaltoRoleResonseDto(Optional<RoleEntity> roleEntity) {
        return roleEntity.map(this::toRoleResponse);
    }

}
