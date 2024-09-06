package com.Graduation_Be.dto.resquest.user;

import com.Graduation_Be.shard.baseModel.BaseModel;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequestDto extends BaseModel {

    String userName;

    String userPassword;

    String phoneNumber;

    String email;

    String address;

    String roleId;

}
