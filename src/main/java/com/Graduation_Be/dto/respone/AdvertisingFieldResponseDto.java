package com.Graduation_Be.dto.respone;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdvertisingFieldResponseDto {
    Long advertisingFieldId;
    String advertisingFieldName;
}