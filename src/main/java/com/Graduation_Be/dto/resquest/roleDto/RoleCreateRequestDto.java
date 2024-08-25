package com.Graduation_Be.dto.resquest.roleDto;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleCreateRequestDto {

    String description;

    String roleCode;

}
