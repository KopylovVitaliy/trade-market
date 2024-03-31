package com.trademarket.services.mapper;

import com.trademarket.model.dto.AdvertisementDto;
import com.trademarket.model.dto.PersonDto;
import com.trademarket.services.entity.Advertisement;
import com.trademarket.services.entity.Person;
import org.mapstruct.Mapper;

@Mapper
public interface AdvertisementMapper extends CommonMapper<AdvertisementDto, Advertisement> {

}
