package com.Graduation_Be.service.impl;

import com.Graduation_Be.dto.respone.AdvertisementResponseDto;
import com.Graduation_Be.dto.respone.RevenueResponseDto;
import com.Graduation_Be.dto.respone.UserResponseDto;
import com.Graduation_Be.dto.resquest.AdvertisingField.AdvertisingFieldResponseDTO;
import com.Graduation_Be.dto.resquest.advertisementDto.AdvertisementCreateRequestDto;
import com.Graduation_Be.dto.resquest.advertisementDto.AdvertisementRequestDto;
import com.Graduation_Be.exception.exceptionOption.ResourceNotFoundException;
import com.Graduation_Be.mapper.AdvertisementMapper;
import com.Graduation_Be.model.*;
import com.Graduation_Be.repository.AdveriserRespository;
import com.Graduation_Be.repository.ApprovalRequestRepository;
import com.Graduation_Be.repository.RevenueRepository;
import com.Graduation_Be.service.AdvertiserService;
import com.Graduation_Be.shard.enums.AdvertisementStatus;
import com.Graduation_Be.shard.enums.ApprovalStatus;
import com.Graduation_Be.shard.enums.MessageSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdvertiserServiceImpl implements AdvertiserService {
    @Autowired
    private AdveriserRespository adveriserRespository;
    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Autowired
    private RevenueRepository revenueRepository;

    @Autowired
    private ApprovalRequestRepository approvalRequestRepository;
    @Override
    public List<AdvertisementResponseDto> getListAdvertiser() {

        return advertisementMapper.toListAdvertiserResponse(adveriserRespository.findAll());
    }

    @Override
    public Optional<AdvertisementResponseDto> getOneAdvertiser(Long id) {
        Optional<AdvertisementEntity> advertisementEntity = Optional.ofNullable(adveriserRespository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageSys.NOT_FOUND)));;
        return advertisementMapper.toOptionalAdvertisementRespone(advertisementEntity);
    }

    @Override
    public void addAdvertiser(AdvertisementCreateRequestDto advertisementCreateRequestDto) {
        AdvertisementEntity entity = AdvertisementEntity.builder()
                .advertisementName(advertisementCreateRequestDto.getAdvertisementName())
                .advertisementLink(advertisementCreateRequestDto.getAdvertisementLink())
                .advertisementPosition(advertisementCreateRequestDto.getAdvertisementPosition())
                .startDate(LocalDate.from(advertisementCreateRequestDto.getStartDate().atStartOfDay()))
                .endDate(LocalDate.from(advertisementCreateRequestDto.getEndDate().atTime(LocalTime.MAX)))
                .price(advertisementCreateRequestDto.getPrice())
                .status(AdvertisementStatus.PENDING)
                .advertisingFields(advertisementCreateRequestDto.getAdvertisingFieldIds().stream()
                        .map(id -> {
                            AdvertisingFieldId fieldId = new AdvertisingFieldId();
                            fieldId.setAdvertisingFieldId(id);
                            return fieldId;
                        })
                        .collect(Collectors.toList()))
                .build();
        adveriserRespository.save(entity);
    }

    @Override
    public AdvertisementResponseDto updateAdvertiser(AdvertisementRequestDto advertisementRequestDto) {
        if(adveriserRespository.existsById(advertisementRequestDto.getAdvertisementId())){
            AdvertisementEntity advertisementEntity = advertisementMapper.toAdvertiserEntity(advertisementRequestDto);
            advertisementEntity.builder()
                    .advertisementName(advertisementRequestDto.getAdvertisementName())
                    .advertisementLink(advertisementRequestDto.getAdvertisementLink())
                    .advertisementPosition(advertisementRequestDto.getAdvertisementPosition())
                    .build();
            advertisementEntity.setStatus(AdvertisementStatus.PENDING);
            adveriserRespository.save(advertisementEntity);
            return advertisementMapper.toAdvertiserResponse(advertisementEntity);
        }
        return null;
    }

    @Override
    public void deleteAdvertiser(Long roleId) {
        adveriserRespository.deleteById(roleId);
    }

    @Override
    public void deleteAllAdvertiser() {
        adveriserRespository.deleteAll();
    }

    @Override
    public void requestApproval(Long id){
        AdvertisementEntity advertisement = adveriserRespository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageSys.NOT_FOUND));

        ApprovalRequestEntity approvalRequest = ApprovalRequestEntity.builder()
                .advertisement(advertisement)
                .requestedAt(LocalDateTime.now())
                .status(ApprovalStatus.PENDING)
                .build();

        approvalRequestRepository.save(approvalRequest);

    }

    @Override
    public void approveAdvertisement(Long id) {
        AdvertisementEntity entity = adveriserRespository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageSys.NOT_FOUND));
        entity.setStatus(AdvertisementStatus.APPROVED);
        adveriserRespository.save(entity);
        ApprovalRequestEntity approvalRequest = approvalRequestRepository.findByAdvertisement(entity)
                .stream().filter((req)->req.getStatus() == ApprovalStatus.PENDING)
                .findFirst()
                .orElseThrow(()->new ResourceNotFoundException(MessageSys.NOT_FOUND));
        approvalRequest.builder()
                .status(ApprovalStatus.APPROVED)
                .approvedAt(LocalDateTime.now());
        approvalRequestRepository.save(approvalRequest);
    }

    @Override
    public RevenueResponseDto getAdvertisementRevenue(Long id) {
        AdvertisementEntity entity = adveriserRespository.findById(id)
        .orElseThrow(()->new ResourceNotFoundException(MessageSys.NOT_FOUND));
        List<RevenueEntity> revenueEntities = revenueRepository.findByAdvertisement(entity);
        BigDecimal toltalRevenue = revenueEntities.stream()
                .map(RevenueEntity::getAmount)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        return RevenueResponseDto.builder()
                .advertisementId(id)
                .amount(toltalRevenue)
                .date(LocalDate.now())
                .build();
    }
}
