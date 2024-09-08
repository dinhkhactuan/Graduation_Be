package com.Graduation_Be.controller;
import com.Graduation_Be.api.ApiResponse;
import com.Graduation_Be.dto.respone.UserResponseDto;
import com.Graduation_Be.dto.resquest.user.UserCreateRequestDto;

import com.Graduation_Be.dto.resquest.user.UserRequestDto;
import com.Graduation_Be.mapper.UserMapper;
import com.Graduation_Be.model.UserEntity;
import com.Graduation_Be.repository.UserRepository;
import com.Graduation_Be.service.impl.UserServiceImpl;
import com.Graduation_Be.shard.enums.MessageSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
//@PreAuthorize("hasRole('admin')")
public class UserController {
    private final UserServiceImpl userServiceImpl;
    UserController(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "")
    public ApiResponse<List<UserResponseDto>> GetAllUser (){
        List<UserResponseDto> listUser =  userServiceImpl.getAllUser();
        return new ApiResponse<>(200, MessageSys.SUSSCESS,listUser);
    }



    @PostMapping(value = "")
    public void addUser (@RequestBody UserCreateRequestDto userCreateRequestDto){
        userServiceImpl.addUser(userCreateRequestDto);
    }

    @PutMapping(value = "")
    public ApiResponse<UserResponseDto> updateUser (@RequestBody UserRequestDto userRequestDto){

        return new ApiResponse<>(200, MessageSys.SUSSCESS, userServiceImpl.updateUser(userRequestDto));
    }

    @DeleteMapping(value = "/{userId}")
    public void  deleteUser (@PathVariable Long userId){
        userServiceImpl.deleteUser(userId);
    }

    @DeleteMapping(value = "")
    public void  deleteAllUser (){
        userServiceImpl.deleteAllUser();
    }

    @GetMapping(value = "/{userId}")
    public Optional<UserResponseDto> getUser (@PathVariable Long userId){
        return  userServiceImpl.getUser(userId);
    }
}
