package com.Graduation_Be.dto.resquest.advertisementDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApprovalRequestCreateDto {
    Long advertisementId;
}
