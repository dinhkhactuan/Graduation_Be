package com.Graduation_Be.dto.respone;

import com.Graduation_Be.model.AdvertisementEntity;
import com.Graduation_Be.model.RoleEntity;
import com.Graduation_Be.shard.baseModel.BaseModel;

import com.Graduation_Be.shard.baseModel.BaseModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDto {

    Long  userId;

    String userName;

    String userPassword;

    String phoneNumber;

    String email;

    String address;

    RoleEntity roleEntity;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    List<AdvertisementEntity> advertisementEntities;

}
