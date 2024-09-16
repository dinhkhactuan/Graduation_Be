package com.Graduation_Be.service;

import com.Graduation_Be.dto.resquest.AdvertisingField.AdvertisingFieldRequestDTO;
import com.Graduation_Be.dto.resquest.AdvertisingField.AdvertisingFieldResponseDTO;
import com.Graduation_Be.model.AdvertisingFieldId;

import java.util.List;
import java.util.Optional;

public interface AdvertisingFieldService {
    public List<AdvertisingFieldId> getAllAdvertisingFields();
    public Optional<AdvertisingFieldId> getAdvertisingFieldById(Long id);
    public AdvertisingFieldId createAdvertisingField(AdvertisingFieldRequestDTO requestDTO);
    public AdvertisingFieldResponseDTO updateAdvertisingField(AdvertisingFieldRequestDTO requestDTO);
    public void deleteAdvertisingField(Long id);
}
