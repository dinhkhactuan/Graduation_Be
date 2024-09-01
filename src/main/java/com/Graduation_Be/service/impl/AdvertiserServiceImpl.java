package com.Graduation_Be.service.impl;

import com.Graduation_Be.dto.respone.AdvertisementResponseDto;
import com.Graduation_Be.dto.respone.UserResponseDto;
import com.Graduation_Be.dto.resquest.advertisementDto.AdvertisementCreateRequestDto;
import com.Graduation_Be.dto.resquest.advertisementDto.AdvertisementRequestDto;
import com.Graduation_Be.exception.exceptionOption.ResourceNotFoundException;
import com.Graduation_Be.mapper.AdvertisementMapper;
import com.Graduation_Be.model.AdvertisementEntity;
import com.Graduation_Be.model.UserEntity;
import com.Graduation_Be.repository.AdveriserRespository;
import com.Graduation_Be.service.AdvertiserService;
import com.Graduation_Be.shard.enums.MessageSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvertiserServiceImpl implements AdvertiserService {
    @Autowired
    private AdveriserRespository adveriserRespository;
    @Autowired
    private AdvertisementMapper advertisementMapper;
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
        adveriserRespository.save(advertisementMapper.toAdvertiserCreateEntity(advertisementCreateRequestDto));
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
}
