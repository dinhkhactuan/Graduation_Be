package com.Graduation_Be.dto.resquest.advertisementDto;

import com.Graduation_Be.shard.enums.ApprovalStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApprovalRequestUpdateDto {
    Long id;
    ApprovalStatus status;
}