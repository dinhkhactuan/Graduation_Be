package com.Graduation_Be.dto.respone;

import com.Graduation_Be.shard.baseModel.BaseModel;
import com.Graduation_Be.shard.enums.AdvertisementStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdvertisementResponseDto {

    Long advertisementId;

    String advertisementName;

    String advertisementLink;

    String advertisementPosition;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    BigDecimal price;
    AdvertisementStatus status;
    List<AdvertisingFieldResponseDto> advertisingFields;
}
