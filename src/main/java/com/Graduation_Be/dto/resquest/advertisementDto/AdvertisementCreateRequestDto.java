
package com.Graduation_Be.dto.resquest.advertisementDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    Long userId;
}
