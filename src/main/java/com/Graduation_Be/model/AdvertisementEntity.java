
package com.Graduation_Be.model;
import com.Graduation_Be.shard.baseModel.BaseModel;
import com.Graduation_Be.shard.enums.AdvertisementStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

//quang cao
@Entity
@Data
@Table(name = "Advertisement")
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Enumerated(EnumType.STRING)
    AdvertisementStatus status;

    @Column(name = "startDate")
    LocalDate startDate;

    @Column(name = "endDate")
    LocalDate endDate;

    @Column(name = "price")
    BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "userId")
    UserEntity userEntity;

    @Column(name = "AdvertisementFieldId")
    Long AdvertisementFieldId;

    @OneToMany(mappedBy = "advertisementEntity",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<AdvertisingFieldId> advertisingFields;
}
