package com.Graduation_Be.dto.respone;

import com.Graduation_Be.shard.enums.ApprovalStatus;
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
    private Long id;
    private Long advertisementId;
    private LocalDateTime requestedAt;
    private LocalDateTime approvedAt;
    private Long approvedBy;
    private ApprovalStatus status;
}