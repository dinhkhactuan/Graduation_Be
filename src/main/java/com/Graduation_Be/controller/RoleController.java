package com.Graduation_Be.controller;

import com.Graduation_Be.api.ApiResponse;
import com.Graduation_Be.dto.respone.RoleResponseDto;
import com.Graduation_Be.service.impl.RoleServiceImpl;
import com.Graduation_Be.shard.enums.MessageSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
