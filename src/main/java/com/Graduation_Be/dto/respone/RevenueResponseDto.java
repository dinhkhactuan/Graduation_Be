package com.Graduation_Be.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RevenueResponseDto {
    private Long id;
    private Long advertisementId;
    private BigDecimal amount;
    private LocalDate date;
}