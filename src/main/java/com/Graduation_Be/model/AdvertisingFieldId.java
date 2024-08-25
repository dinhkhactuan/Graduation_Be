
package com.Graduation_Be.model;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


//Nha quang cao
@Entity
@Data
@Table(name = "AdvertisingField")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdvertisingFieldId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AdvertisingFieldId")
    Long advertisingFieldId;

    @Column(name = "advertisingFieldName")
    String advertisingFieldName;

    @ManyToOne
    @JoinColumn(name = "advertisementId")
    AdvertisementEntity advertisementEntity;

}
