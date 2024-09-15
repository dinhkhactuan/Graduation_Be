package com.Graduation_Be.dto.resquest.AdvertisingField;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisingFieldResponseDTO {
    private Long advertisingFieldId;
    private String advertisingFieldName;
}