package com.Graduation_Be.service;
import com.Graduation_Be.dto.respone.UserResponseDto;
import com.Graduation_Be.dto.resquest.user.UserCreateRequestDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public void addUser (UserCreateRequestDto userCreateRequestDto);

    public List<UserResponseDto> getAllUser();

    public void deleteUser (Long userId);

    public Optional<UserResponseDto> getUser (Long userId);
    
}
