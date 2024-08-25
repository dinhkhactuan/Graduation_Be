
package com.Graduation_Be.dto.resquest.advertisementDto;
import com.Graduation_Be.shard.baseModel.BaseModel;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdvertisementCreateRequestDto extends BaseModel {

    String advertisementName;

    String advertisementLink;

    String advertisementPosition;


}
