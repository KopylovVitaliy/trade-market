package com.trademarket.services.mapper;

import com.trademarket.model.dto.AdvertisementDto;
import com.trademarket.model.dto.PersonDto;
import com.trademarket.services.entity.Advertisement;
import com.trademarket.services.entity.Person;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AdvertisementMapper implements CommonMapper<AdvertisementDto, Advertisement> {
    @Override
    public Advertisement toEntity(AdvertisementDto dto) {
        return Advertisement.builder()
                .id(dto.getId())
                .price(dto.getPrice())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }

    @Override
    public AdvertisementDto toDto(Advertisement entity) {
        return AdvertisementDto.builder()
                .id(entity.getId())
                .price(entity.getPrice())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .build();
    }

    @Override
    public List<Advertisement> toEntityList(List<AdvertisementDto> list) {
        return list.stream()
                .map(this::toEntity)
                .toList();
    }

    @Override
    public List<AdvertisementDto> toDtoList(List<Advertisement> list) {
        return list.stream()
                .map(this::toDto)
                .toList();
    }
}
