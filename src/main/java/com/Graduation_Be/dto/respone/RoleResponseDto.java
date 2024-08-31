package com.Graduation_Be.dto.respone;

import com.Graduation_Be.shard.baseModel.BaseModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponseDto extends BaseModel {

    Long roleId;

    String description;

    String roleCode;


}
