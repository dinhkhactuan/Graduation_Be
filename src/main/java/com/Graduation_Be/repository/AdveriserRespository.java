
package com.Graduation_Be.repository;

import com.Graduation_Be.model.AdvertisementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdveriserRespository extends JpaRepository<AdvertisementEntity, Long> {
    List<AdvertisementEntity> getAdvertisingFieldsByAdvertisementId(Long advertisementId);

    List<AdvertisementEntity> findAllByUserId(@Param("userId") Long userId);
}
