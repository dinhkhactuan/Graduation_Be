
package com.Graduation_Be.model;
import com.Graduation_Be.shard.baseModel.BaseModel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

//quang cao
@Entity
@Data
@Table(name = "Advertisement")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdvertisementEntity extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advertisementId")
    Long advertisementId;

    @Column(name = "advertisementName")
    String advertisementName;

    @Column(name = "advertisementLink")
    String advertisementLink;

    @Column(name = "advertisementPosition")
    String advertisementPosition;

    @ManyToOne
    @JoinColumn(name = "userId")
    UserEntity userEntity;

    @OneToMany(mappedBy = "advertisementEntity")
    List<AdvertisingFieldId> advertisingFields;
}
