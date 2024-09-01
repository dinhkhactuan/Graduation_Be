
package com.Graduation_Be.dto.resquest.advertisementDto;
import com.Graduation_Be.shard.baseModel.BaseModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdvertisementCreateRequestDto {

    String advertisementName;

    String advertisementLink;

    String advertisementPosition;

}
