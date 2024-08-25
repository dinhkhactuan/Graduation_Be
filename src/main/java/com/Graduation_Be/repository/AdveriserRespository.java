
package com.Graduation_Be.repository;

import com.Graduation_Be.model.AdvertisementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdveriserRespository extends JpaRepository<AdvertisementEntity, Long> {
}
