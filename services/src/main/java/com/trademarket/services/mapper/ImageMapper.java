package com.trademarket.services.mapper;

import com.trademarket.model.dto.ImageDto;
import com.trademarket.model.dto.PersonDto;
import com.trademarket.services.entity.Image;
import com.trademarket.services.entity.Person;
import org.mapstruct.Mapper;

@Mapper
public interface ImageMapper extends CommonMapper<ImageDto, Image> {
}
