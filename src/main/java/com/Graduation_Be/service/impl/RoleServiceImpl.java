package com.Graduation_Be.service.impl;

import com.Graduation_Be.dto.respone.RoleResponseDto;
import com.Graduation_Be.dto.resquest.roleDto.RoleCreateRequestDto;
import com.Graduation_Be.dto.resquest.roleDto.RoleRequestDto;
import com.Graduation_Be.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public List<RoleResponseDto> getListRole() {
        return null;
    }

    @Override
    public RoleResponseDto getOneRole() {
        return null;
    }

    @Override
    public void addRole(RoleCreateRequestDto roleCreateRequestDto) {

    }

    @Override
    public void updateRole(RoleRequestDto roleRequestDto) {

    }

    @Override
    public void deleteRole(Long roleId) {

    }
}
