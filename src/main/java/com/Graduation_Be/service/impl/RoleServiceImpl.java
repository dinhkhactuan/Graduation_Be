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
import jakarta.persistence.EntityNotFoundException;
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
        return roleMapper.toListRoleResponse(roleRepository.findAll());
    }

    @Override
    public Optional<RoleResponseDto> getOneRole(String id) {

        Optional<RoleEntity> roleEntity = Optional.ofNullable(roleRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException(MessageSys.USER_NOT_EXIST)));
        return roleMapper.toOptinaltoRoleResonseDto(roleEntity);
    }

    @Override
    public void addRole(RoleCreateRequestDto roleCreateRequestDto) {
            RoleEntity roleEntity = roleMapper.toRoleCreateEntity(roleCreateRequestDto);
            roleRepository.save(roleEntity);
    }

    @Override
    public RoleResponseDto updateRole(RoleRequestDto roleRequestDto) {

        RoleEntity existingRole = roleRepository.findById(roleRequestDto.getRoleId())
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + roleRequestDto.getRoleId()));
        existingRole.setRoleCode(roleRequestDto.getRoleCode());
        existingRole.setDescription(roleRequestDto.getDescription());
        roleRepository.save(existingRole);
        return roleMapper.toRoleResponse(existingRole);
    }

    @Override
    public void deleteRole(Long roleId) {
        if(roleId == null){
            return;
        }
        if(roleRepository.existsById(roleId)){
            roleRepository.deleteById(roleId);
        }

    }
}
