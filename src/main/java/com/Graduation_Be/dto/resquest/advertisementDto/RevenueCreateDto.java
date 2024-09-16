package com.Graduation_Be.dto.resquest.advertisementDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RevenueCreateDto {
    Long advertisementId;
    BigDecimal amount;
    LocalDate date;
}