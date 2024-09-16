package com.Graduation_Be.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Revenue")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RevenueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "advertisementId")
    AdvertisementEntity advertisement;

    @Column(name = "amount")
    BigDecimal amount;

    @Column(name = "date")
    LocalDate date;
}
