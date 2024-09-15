package com.Graduation_Be.service.impl;

import com.Graduation_Be.dto.resquest.AdvertisingField.AdvertisingFieldRequestDTO;
import com.Graduation_Be.dto.resquest.AdvertisingField.AdvertisingFieldResponseDTO;
import com.Graduation_Be.mapper.AdvertisingFieldMapper;
import com.Graduation_Be.model.AdvertisingFieldId;
import com.Graduation_Be.repository.AdvertisingFieldRepository;
import com.Graduation_Be.service.AdvertisingFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvertisingFieldServiceImpl implements AdvertisingFieldService {
    @Autowired
    private AdvertisingFieldRepository repository;

    @Autowired
    private AdvertisingFieldMapper advertisingFieldMapper;
    @Override
    public List<AdvertisingFieldId> getAllAdvertisingFields() {
        return repository.findAll();
    }

    @Override
    public Optional<AdvertisingFieldId> getAdvertisingFieldById(Long id) {
        return repository.findById(id);
    }

    @Override
    public AdvertisingFieldId createAdvertisingField(AdvertisingFieldRequestDTO requestDTO) {
        return repository.save(advertisingFieldMapper.toAdvertisingFieldId(requestDTO));
    }


    @Override
    public AdvertisingFieldResponseDTO updateAdvertisingField( AdvertisingFieldRequestDTO requestDTO) {
        AdvertisingFieldId entity = repository.findByAdvertisingFieldName(requestDTO.getAdvertisingFieldName())
                .orElseThrow(() -> new RuntimeException("Advertising Field not found"));
        advertisingFieldMapper.toAdvertisingFieldId(requestDTO);
        AdvertisingFieldId updatedEntity = repository.save(entity);
        return advertisingFieldMapper.toAdvertisingFieldIdResponseDTO(updatedEntity);
    }

    @Override
    public void deleteAdvertisingField(Long id) {
        repository.deleteById(id);
    }
}
