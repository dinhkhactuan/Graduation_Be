package com.Graduation_Be.repository;

import com.Graduation_Be.model.AdvertisementEntity;
import com.Graduation_Be.model.AdvertisingFieldId;
import com.Graduation_Be.model.ApprovalRequestEntity;
import com.Graduation_Be.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertisingFieldRepository extends JpaRepository<AdvertisingFieldId, Long> {
    Optional<AdvertisingFieldId> findByAdvertisingFieldName(String username);

}