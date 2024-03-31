package com.trademarket.services.mapper;

import com.trademarket.model.dto.PersonDto;
import com.trademarket.services.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PersonMapper implements CommonMapper<PersonDto, Person> {

    public Person toEntity(PersonDto dto) {
        return Person.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .role(dto.getRole())
                .secondName(dto.getSecondName())
                .firstName(dto.getFirstName())
                .build();
    }

    public PersonDto toDto(Person entity) {
        return PersonDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .role(entity.getRole())
                .secondName(entity.getSecondName())
                .firstName(entity.getFirstName())
                .build();
    }

    public List<Person> toEntityList(List<PersonDto> list) {
        return list.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<PersonDto> toDtoList(List<Person> list) {
        return list.stream()
                .map(this::toDto)
                .toList();
    }
}
