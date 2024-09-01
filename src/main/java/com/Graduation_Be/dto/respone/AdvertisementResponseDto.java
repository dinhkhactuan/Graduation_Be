package com.Graduation_Be.dto.respone;

import com.Graduation_Be.shard.baseModel.BaseModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


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
}
