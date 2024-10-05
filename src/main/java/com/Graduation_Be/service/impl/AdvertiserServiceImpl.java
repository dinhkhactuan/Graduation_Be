package com.Graduation_Be.service.impl;

import com.Graduation_Be.dto.respone.AdvertisementResponseDto;
import com.Graduation_Be.dto.respone.AdvertisingFieldResponseDto;
import com.Graduation_Be.dto.respone.RevenueResponseDto;
import com.Graduation_Be.dto.resquest.advertisementDto.AdvertisementCreateRequestDto;
import com.Graduation_Be.dto.resquest.advertisementDto.AdvertisementRequestDto;
import com.Graduation_Be.exception.exceptionOption.ResourceNotFoundException;
import com.Graduation_Be.mapper.AdvertisementMapper;
import com.Graduation_Be.model.AdvertisementEntity;
import com.Graduation_Be.model.AdvertisingFieldId;
import com.Graduation_Be.model.ApprovalRequestEntity;
import com.Graduation_Be.model.RevenueEntity;
import com.Graduation_Be.repository.*;
import com.Graduation_Be.service.AdvertiserService;
import com.Graduation_Be.shard.enums.AdvertisementStatus;
import com.Graduation_Be.shard.enums.ApprovalStatus;
import com.Graduation_Be.shard.enums.MessageSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdvertiserServiceImpl implements AdvertiserService {
    @Autowired
    private AdveriserRespository adveriserRespository;

    @Autowired
    private AdvertisingFieldRepository advertisingFieldRepository;
    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Autowired
    private RevenueRepository revenueRepository;

    @Autowired
    private ApprovalRequestRepository approvalRequestRepository;
    @Override
    public List<AdvertisementResponseDto> getListAdvertiser() {
        List<AdvertisementEntity> advertisementEntities = adveriserRespository.findAll();

        // Collect unique AdvertisingFieldIds
        Set<Long> allFieldIds = advertisementEntities.stream()
                .map(AdvertisementEntity::getAdvertisementFieldId)
                .collect(Collectors.toSet());

        // Fetch all AdvertisingFields
        List<AdvertisingFieldId> allFields = advertisingFieldRepository.findAllById(allFieldIds);

        // Map AdvertisingFields to AdvertisingFieldResponseDto
        Map<Long, AdvertisingFieldResponseDto> fieldDtoMap = allFields.stream()
                .collect(Collectors.toMap(
                        AdvertisingFieldId::getAdvertisingFieldId,
                        field -> new AdvertisingFieldResponseDto(field.getAdvertisingFieldId(), field.getAdvertisingFieldName())
                ));

        // Map AdvertisementEntities to AdvertisementResponseDtos
        return advertisementEntities.stream().map(entity -> {
            AdvertisementResponseDto dto = new AdvertisementResponseDto();
            dto.setAdvertisementId(entity.getAdvertisementId());
            dto.setAdvertisementName(entity.getAdvertisementName());
            dto.setAdvertisementLink(entity.getAdvertisementLink());
            dto.setAdvertisementPosition(entity.getAdvertisementPosition());
            dto.setStartTime(entity.getStartDate() != null ? entity.getStartDate().atStartOfDay() : null);
            dto.setEndTime(entity.getEndDate() != null ? entity.getEndDate().atTime(LocalTime.MAX) : null);
            dto.setPrice(entity.getPrice());
            dto.setStatus(entity.getStatus());
            dto.setAdvertisingFields(adveriserRespository.getAdvertisingFieldsByAdvertisementId(entity.getAdvertisementId()).stream()
                    .map(fieldId -> fieldDtoMap.get(fieldId.getAdvertisementFieldId()))
                    .collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<AdvertisementResponseDto> getListAdvertiserByUser(Long userId) {
            List<AdvertisementEntity> advertisementEntities = adveriserRespository.findAllByUserId(userId);

        return  advertisementMapper.toListAdvertiserResponse(advertisementEntities);
//        // Collect unique AdvertisingFieldIds
//        Set<Long> allFieldIds = advertisementEntities.stream()
//                .map(AdvertisementEntity::getAdvertisementFieldId)
//                .collect(Collectors.toSet());
//
//        // Fetch all AdvertisingFields
//        List<AdvertisingFieldId> allFields = advertisingFieldRepository.findAllById(allFieldIds);
//
//        // Map AdvertisingFields to AdvertisingFieldResponseDto
//        Map<Long, AdvertisingFieldResponseDto> fieldDtoMap = allFields.stream()
//                .collect(Collectors.toMap(
//                        AdvertisingFieldId::getAdvertisingFieldId,
//                        field -> new AdvertisingFieldResponseDto(field.getAdvertisingFieldId(), field.getAdvertisingFieldName())
//                ));
//
//        // Map AdvertisementEntities to AdvertisementResponseDtos
//        return advertisementEntities.stream().map(entity -> {
//            AdvertisementResponseDto dto = new AdvertisementResponseDto();
//            dto.setAdvertisementId(entity.getAdvertisementId());
//            dto.setAdvertisementName(entity.getAdvertisementName());
//            dto.setAdvertisementLink(entity.getAdvertisementLink());
//            dto.setAdvertisementPosition(entity.getAdvertisementPosition());
//            dto.setStartTime(entity.getStartDate() != null ? entity.getStartDate().atStartOfDay() : null);
//            dto.setEndTime(entity.getEndDate() != null ? entity.getEndDate().atTime(LocalTime.MAX) : null);
//            dto.setPrice(entity.getPrice());
//            dto.setStatus(entity.getStatus());
//            dto.setAdvertisingFields(adveriserRespository.getAdvertisingFieldsByAdvertisementId(entity.getAdvertisementId()).stream()
//                    .map(fieldId -> fieldDtoMap.get(fieldId.getAdvertisementFieldId()))
//                    .collect(Collectors.toList()));
//            return dto;
//        }).collect(Collectors.toList());
    }

//    update quangr cao theo user
    @Override
    public AdvertisementResponseDto updateAdvertiserByUser(AdvertisementRequestDto requestDto) {
        AdvertisementEntity entity = adveriserRespository.findById(requestDto.getAdvertisementId())
                .orElseThrow();

        entity.setAdvertisementName(requestDto.getAdvertisementName());
        entity.setAdvertisementLink(requestDto.getAdvertisementLink());
        entity.setAdvertisementPosition(requestDto.getAdvertisementPosition());
        entity.setStartDate(requestDto.getStartDate());
        entity.setEndDate(requestDto.getEndDate());
        entity.setPrice(requestDto.getPrice());
        entity.setStatus(requestDto.getStatus());
        entity.setUserId(requestDto.getUserId());
        entity.setUserId(requestDto.getUserId());

        if (!requestDto.getAdvertisingFieldIds().isEmpty()) {
            entity.setAdvertisementFieldId(requestDto.getAdvertisingFieldIds().get(0));
        }

        AdvertisementEntity advertisement = adveriserRespository.save(entity);
        return  advertisementMapper.toAdvertiserResponse(advertisement);
    }

    @Override
    public Optional<AdvertisementResponseDto> getOneAdvertiser(Long id) {
        Optional<AdvertisementEntity> advertisementEntity = Optional.ofNullable(adveriserRespository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageSys.NOT_FOUND)));
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
                .userId(advertisementCreateRequestDto.getUserId())
                .AdvertisementFieldId(advertisementCreateRequestDto.getAdvertisingFieldIds().get(0))
                .build();
        adveriserRespository.save(entity);
    }

    @Override
    public AdvertisementResponseDto updateAdvertiser(AdvertisementRequestDto advertisementRequestDto) {
        if(adveriserRespository.existsById(advertisementRequestDto.getAdvertisementId())){
            AdvertisementEntity advertisementEntity = advertisementMapper.toAdvertiserEntity(advertisementRequestDto);
            AdvertisementEntity.builder()
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
        ApprovalRequestEntity.builder()
                .status(ApprovalStatus.APPROVED)
                .approvedAt(LocalDateTime.now())
                .build();
        approvalRequestRepository.save(approvalRequest);
        approvalRequestRepository.deleteById(approvalRequest.getId());
    }

    @Override
    public RevenueResponseDto getAdvertisementRevenue(Long id) {
        AdvertisementEntity entity = adveriserRespository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageSys.NOT_FOUND));

        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = entity.getStartDate();
        LocalDate endDate = entity.getEndDate();

        // Tính số ngày quảng cáo đã chạy
        long totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        long daysRun = ChronoUnit.DAYS.between(startDate, currentDate) + 1;
        daysRun = Math.min(daysRun, totalDays); // Đảm bảo không vượt quá tổng số ngày

        // Tính doanh thu dựa trên số ngày đã chạy
        BigDecimal dailyRate = entity.getPrice().divide(BigDecimal.valueOf(totalDays), 2, RoundingMode.HALF_UP);
        BigDecimal totalRevenue = dailyRate.multiply(BigDecimal.valueOf(daysRun));

        // Cập nhật hoặc tạo mới RevenueEntity
        RevenueEntity revenueEntity = revenueRepository.findByAdvertisement(entity);
        if (revenueEntity == null) {
            revenueEntity = new RevenueEntity();
            revenueEntity.setAdvertisement(entity);
        }
        revenueEntity.setAmount(totalRevenue);
        revenueEntity.setDate(currentDate);
        revenueEntity = revenueRepository.save(revenueEntity);

        // Đảm bảo rằng id không null sau khi lưu
        if (revenueEntity.getId() == null) {
            throw new RuntimeException("Failed to generate ID for RevenueEntity");
        }

        return RevenueResponseDto.builder()
                .id(revenueEntity.getId())
                .advertisementId(entity.getAdvertisementId())
                .amount(revenueEntity.getAmount())
                .date(revenueEntity.getDate())
                .build();
    }

    @Override
    public BigDecimal  getAllRevenue() {
        List<AdvertisementEntity> approvedAdvertisers = adveriserRespository.findByStatus(AdvertisementStatus.APPROVED);

        return approvedAdvertisers.stream()
                .map(AdvertisementEntity::getPrice)  // Giả sử có phương thức getPrice() trả về BigDecimal
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
