package com.Graduation_Be.service.impl;

import com.Graduation_Be.dto.respone.RoleResponseDto;
import com.Graduation_Be.dto.resquest.roleDto.RoleCreateRequestDto;
import com.Graduation_Be.dto.resquest.roleDto.RoleRequestDto;
import com.Graduation_Be.exception.exceptionOption.ResourceNotFoundException;
import com.Graduation_Be.mapper.RoleMapper;
import com.Graduation_Be.model.RoleEntity;
import com.Graduation_Be.repository.RoleRepository;
import com.Graduation_Be.service.RoleService;
import com.Graduation_Be.shard.enums.MessageSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleResponseDto> getListRole() {
        return null;
    }

    @Override
    public Optional<RoleResponseDto> getOneRole(String id) {

        Optional<RoleEntity> roleEntity = Optional.ofNullable(roleRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException(MessageSys.USER_NOT_EXIST)));
        return roleMapper.toOptinaltoRoleResonseDto(roleEntity);
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
