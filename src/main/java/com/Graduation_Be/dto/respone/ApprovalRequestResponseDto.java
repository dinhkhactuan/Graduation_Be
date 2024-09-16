package com.Graduation_Be.dto.respone;

import com.Graduation_Be.model.AdvertisementEntity;
import com.Graduation_Be.shard.enums.ApprovalStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApprovalRequestResponseDto {

    Long id;

    AdvertisementEntity advertisement;

    LocalDateTime requestedAt;

    LocalDateTime approvedAt;

    ApprovalStatus status;
}