package com.Graduation_Be.controller;

import com.Graduation_Be.api.ApiResponse;
import com.Graduation_Be.dto.respone.RoleResponseDto;
import com.Graduation_Be.dto.resquest.roleDto.RoleCreateRequestDto;
import com.Graduation_Be.dto.resquest.roleDto.RoleRequestDto;
import com.Graduation_Be.service.impl.RoleServiceImpl;
import com.Graduation_Be.shard.enums.MessageSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "role")
public class RoleController {
    @Autowired
    private  RoleServiceImpl roleService;

    @GetMapping(value = "")
    public ApiResponse<List<RoleResponseDto>>getRoles (){
        List<RoleResponseDto> listRole = roleService.getListRole();
       return new ApiResponse<>(200 , MessageSys.SUSSCESS,listRole);
    }

    @GetMapping(value = "/{id}")
    public ApiResponse<Optional<RoleResponseDto>> getRole(@PathVariable String id){
        Optional<RoleResponseDto> role = roleService.getOneRole(id);
        return new ApiResponse<>(200 ,MessageSys.SUSSCESS , role);
    }

    @PostMapping(value = "")
    public ApiResponse<?> addRole (@RequestBody RoleCreateRequestDto roleCreateRequestDto){
        if(roleCreateRequestDto.getRoleCode()==""){
           return new ApiResponse<>(400,MessageSys.NOT_FOUND,null);
        }
        roleService.addRole(roleCreateRequestDto);
        return new ApiResponse<>(200,MessageSys.SUSSCESS,null);
    }

    @PutMapping(value = "")
    public ApiResponse<RoleResponseDto> updateRole(@RequestBody RoleRequestDto roleRequestDto){

        return new ApiResponse<>(200,MessageSys.SUSSCESS,roleService.updateRole(roleRequestDto));
    }

    @DeleteMapping(value = "/{roleId}")
    public ApiResponse<?> deleteRole(@PathVariable Long roleId){
        roleService.deleteRole(roleId);
        return new ApiResponse<>(200,MessageSys.SUSSCESS,null);
    }
}
