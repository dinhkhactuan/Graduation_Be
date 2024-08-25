package com.Graduation_Be.service.impl;
import com.Graduation_Be.dto.respone.UserResponseDto;
import com.Graduation_Be.dto.resquest.user.UserCreateRequestDto;
import com.Graduation_Be.exception.exceptionOption.ResourceNotFoundException;
import com.Graduation_Be.mapper.UserMapper;
import com.Graduation_Be.model.UserEntity;
import com.Graduation_Be.repository.UserRepository;
import com.Graduation_Be.service.UserService;
import com.Graduation_Be.shard.enums.MessageSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(UserCreateRequestDto userCreateRequestDto) {
        UserEntity userEntity = userMapper.toUserCreateEntity(userCreateRequestDto);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        userEntity.setUserPassword(passwordEncoder.encode((userCreateRequestDto.getUserPassword())));
        userRepository.save(userEntity);
    }

    public List<UserResponseDto> getAllUser() {
        return userMapper.toListUserRespone(userRepository.findAll());
    }

    @Override
    public void deleteUser(Long userId) {
        if(userId == null ) throw  new ResourceNotFoundException(MessageSys.NOT_FOUND);
        userRepository.deleteById(userId);
    }
    @Override
    public Optional<UserResponseDto> getUser(Long userId) {
        Optional<UserEntity> userEntity = Optional.ofNullable(userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageSys.USER_NOT_EXIST)));;
        return userMapper.toOptionalUserRespone(userEntity);
    }
}
