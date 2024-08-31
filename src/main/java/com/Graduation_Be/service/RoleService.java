package com.Graduation_Be.service;


import com.Graduation_Be.api.ApiResponse;
import com.Graduation_Be.dto.respone.RoleResponseDto;
import com.Graduation_Be.dto.resquest.roleDto.RoleCreateRequestDto;
import com.Graduation_Be.dto.resquest.roleDto.RoleRequestDto;
import com.Graduation_Be.model.RoleEntity;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    //getList
    public List<RoleResponseDto> getListRole();

    //getOne
    public Optional<RoleResponseDto> getOneRole(String id);

    //add
    public void addRole(RoleCreateRequestDto roleCreateRequestDto);

    //update

    public RoleResponseDto updateRole(RoleRequestDto roleRequestDto);

//    delete
    public void deleteRole (Long roleId);
}
