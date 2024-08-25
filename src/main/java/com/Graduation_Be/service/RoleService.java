package com.Graduation_Be.service;


import com.Graduation_Be.dto.respone.RoleResponseDto;
import com.Graduation_Be.dto.resquest.roleDto.RoleCreateRequestDto;
import com.Graduation_Be.dto.resquest.roleDto.RoleRequestDto;

import java.util.List;

public interface RoleService {

    //getList
    public List<RoleResponseDto> getListRole();

    //getOne
    public RoleResponseDto getOneRole();

    //add
    public void addRole(RoleCreateRequestDto roleCreateRequestDto);

    //update

    public void updateRole(RoleRequestDto roleRequestDto);

//    delete
    public void deleteRole (Long roleId);
}
