package com.Graduation_Be.controller;

import com.Graduation_Be.dto.resquest.AdvertisingField.AdvertisingFieldRequestDTO;
import com.Graduation_Be.dto.resquest.AdvertisingField.AdvertisingFieldResponseDTO;
import com.Graduation_Be.mapper.AdvertisingFieldMapper;
import com.Graduation_Be.model.AdvertisingFieldId;
import com.Graduation_Be.service.impl.AdvertisingFieldServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/advertising-fields")
public class AdvertisingFieldController {

    @Autowired
    private AdvertisingFieldServiceImpl service;
    @Autowired
    private AdvertisingFieldMapper advertisingFieldMapper;

    @GetMapping
    public List<AdvertisingFieldResponseDTO> getAllAdvertisingFields() {
        return advertisingFieldMapper.toListAdvertisingFieldIdResponseDTO(service.getAllAdvertisingFields());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertisingFieldId> getAdvertisingFieldById(@PathVariable Long id) {
        return service.getAdvertisingFieldById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AdvertisingFieldResponseDTO createAdvertisingField(@RequestBody AdvertisingFieldRequestDTO requestDTO) {
        return advertisingFieldMapper.toAdvertisingFieldIdResponseDTO(service.createAdvertisingField(requestDTO));
    }

    @PutMapping("")
    public ResponseEntity<AdvertisingFieldResponseDTO> updateAdvertisingField(@RequestBody AdvertisingFieldRequestDTO requestDTO) {
        return ResponseEntity.ok(service.updateAdvertisingField( requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvertisingField(@PathVariable Long id) {
        service.deleteAdvertisingField(id);
        return ResponseEntity.noContent().build();
    }
}