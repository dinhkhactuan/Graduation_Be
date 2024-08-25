package com.Graduation_Be.controller;
import com.Graduation_Be.dto.respone.UserResponseDto;
import com.Graduation_Be.dto.resquest.user.UserCreateRequestDto;

import com.Graduation_Be.model.UserEntity;
import com.Graduation_Be.service.impl.UserServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserServiceImpl userServiceImpl;
    UserController(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping(value = "")
    public List<UserResponseDto> GetAllUser (){
        List<UserResponseDto> listUser =  userServiceImpl.getAllUser();
        return listUser;
    }

    @PostMapping(value = "")
    public void addUser (@RequestBody UserCreateRequestDto userCreateRequestDto){
        userServiceImpl.addUser(userCreateRequestDto);
    }

    @DeleteMapping(value = "/{userId}")
    public void  deleteUser (@PathVariable Long userId){
        userServiceImpl.deleteUser(userId);
    }

    @GetMapping(value = "/{userId}")
    public Optional<UserResponseDto> getUser (@PathVariable Long userId){
        return  userServiceImpl.getUser(userId);
    }
}
