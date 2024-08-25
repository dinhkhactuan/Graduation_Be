package com.Graduation_Be.mapper;

import com.Graduation_Be.dto.respone.RoleResponseDto;
import com.Graduation_Be.dto.resquest.roleDto.RoleCreateRequestDto;
import com.Graduation_Be.dto.resquest.roleDto.RoleRequestDto;
import com.Graduation_Be.model.RoleEntity;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {
    RoleEntity toRoleEntity (RoleRequestDto roleRequestDto);

    RoleEntity toRoleCreateEntity (RoleCreateRequestDto roleCreateRequestDto);

    RoleResponseDto toRoleResponse (RoleEntity roleEntity);
}
