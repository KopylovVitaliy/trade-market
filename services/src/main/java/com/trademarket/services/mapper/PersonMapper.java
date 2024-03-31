package com.trademarket.services.mapper;

import com.trademarket.model.dto.PersonDto;
import com.trademarket.services.entity.Person;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper extends CommonMapper<PersonDto, Person> {

}
