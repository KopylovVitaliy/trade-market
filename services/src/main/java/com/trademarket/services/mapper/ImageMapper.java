package com.trademarket.services.mapper;

import com.trademarket.model.dto.ImageDto;
import com.trademarket.services.entity.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@RequiredArgsConstructor
@Component
public class ImageMapper implements CommonMapper<ImageDto, Image> {

    @Override
    public Image toEntity(ImageDto dto) {
        return Image.builder()
                .id(dto.getId())
                .build();
    }

    @Override
    public ImageDto toDto(Image entity) {
        return ImageDto.builder()
                .id(entity.getId())
                .build();
    }

    @Override
    public List<Image> toEntityList(List<ImageDto> list) {
        return list.stream()
                .map(this::toEntity)
                .toList();
    }

    @Override
    public List<ImageDto> toDtoList(List<Image> list) {
        return list.stream()
                .map(this::toDto)
                .toList();
    }
}
