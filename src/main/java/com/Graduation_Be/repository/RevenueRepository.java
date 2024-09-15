package com.Graduation_Be.repository;

import com.Graduation_Be.model.AdvertisementEntity;
import com.Graduation_Be.model.RevenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RevenueRepository extends JpaRepository<RevenueEntity, Long> {
    List<RevenueEntity> findByAdvertisement(AdvertisementEntity advertisement);
}