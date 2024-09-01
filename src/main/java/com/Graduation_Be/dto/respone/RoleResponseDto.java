package com.Graduation_Be.dto.respone;

import com.Graduation_Be.shard.baseModel.BaseModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponseDto {

    Long roleId;

    String description;

    String roleCode;

    private LocalDateTime startTime;

    private LocalDateTime endTime;


}
