
package com.Graduation_Be.dto.resquest.advertisementDto;
import com.Graduation_Be.shard.baseModel.BaseModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdvertisementCreateRequestDto {

    String advertisementName;
    String advertisementLink;
    String advertisementPosition;
    LocalDate startDate;
    LocalDate endDate;
    BigDecimal price;
    List<Long> advertisingFieldIds;

}
