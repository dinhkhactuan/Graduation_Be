
package com.Graduation_Be.model;

import com.Graduation_Be.shard.baseModel.BaseModel;
import com.Graduation_Be.shard.enums.AdvertisementStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @Column(name = "userId")
    Long userId;

    @Column(name = "AdvertisementFieldId")
    Long AdvertisementFieldId;

    @OneToMany(mappedBy = "advertisementEntity",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<AdvertisingFieldId> advertisingFields;
}
