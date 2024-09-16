package com.Graduation_Be.repository;

import com.Graduation_Be.model.AdvertisementEntity;
import com.Graduation_Be.model.ApprovalRequestEntity;
import com.Graduation_Be.shard.enums.ApprovalStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovalRequestRepository extends JpaRepository<ApprovalRequestEntity, Long> {
    List<ApprovalRequestEntity> findByAdvertisement(AdvertisementEntity advertisement);

    List<ApprovalRequestEntity> findByStatusOrderByRequestedAtDesc(ApprovalStatus status, Pageable pageable);

}
