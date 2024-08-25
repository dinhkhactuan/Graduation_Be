package com.Graduation_Be.mapper;
import com.Graduation_Be.dto.respone.UserResponseDto;
import com.Graduation_Be.dto.resquest.user.UserCreateRequestDto;
import com.Graduation_Be.dto.resquest.user.UserRequestDto;
import com.Graduation_Be.model.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserEntity toUserCreateEntity (UserCreateRequestDto userCreateRequestDto);

    UserEntity toUserEntity (UserRequestDto userRequestDto);

    UserResponseDto toUserRespone (UserEntity userEntity);

    List<UserResponseDto> toListUserRespone (List<UserEntity> userEntity);

    default Optional<UserResponseDto> toOptionalUserRespone(Optional<UserEntity> userEntity) {
        return userEntity.map(this::toUserRespone);
    }
}
