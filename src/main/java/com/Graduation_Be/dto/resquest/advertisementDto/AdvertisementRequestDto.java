package com.Graduation_Be.dto.resquest.advertisementDto;
import com.Graduation_Be.shard.baseModel.BaseModel;
import com.Graduation_Be.shard.enums.AdvertisementStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdvertisementRequestDto  {

    Long advertisementId;
    String advertisementName;
    String advertisementLink;
    String advertisementPosition;
    LocalDate startDate;
    LocalDate endDate;
    BigDecimal price;
    AdvertisementStatus status;
    List<Long> advertisingFieldIds;


}
