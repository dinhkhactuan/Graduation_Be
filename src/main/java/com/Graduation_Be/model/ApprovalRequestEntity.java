package com.Graduation_Be.model;

import com.Graduation_Be.shard.baseModel.BaseModel;
import com.Graduation_Be.shard.enums.AdvertisementStatus;
import com.Graduation_Be.shard.enums.ApprovalStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "ApprovalRequest")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApprovalRequestEntity extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "advertisementId")
    AdvertisementEntity advertisement;

    @Column(name = "requestedAt")
    LocalDateTime requestedAt;

    @Column(name = "approvedAt")
    LocalDateTime approvedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    ApprovalStatus status;

}
