package com.Graduation_Be.dto.resquest.advertisementDto;
import com.Graduation_Be.shard.baseModel.BaseModel;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdvertisementRequestDto extends BaseModel {

    Long advertisementId;

    String advertisementName;

    String advertisementLink;

    String advertisementPosition;


}
