package com.Graduation_Be.dto.resquest.roleDto;

import com.Graduation_Be.shard.baseModel.BaseModel;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequestDto extends BaseModel {


    Long roleId;

    String description;

    String roleCode;

}
