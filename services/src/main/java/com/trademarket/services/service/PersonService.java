package com.trademarket.services.service;

import com.trademarket.model.dto.PersonDto;
import com.trademarket.services.entity.Person;
import com.trademarket.services.mapper.PersonMapper;
import com.trademarket.services.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService
        extends AbstractService<PersonRepository, PersonDto, Person, PersonMapper> {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public PersonRepository getRepository() {
        return personRepository;
    }

    @Override
    public PersonMapper getMapper() {
        return personMapper;
    }

    @Override
    public PersonDto save(PersonDto dto) {
        if (getRepository().findPersonByEmail(dto.getEmail()).isPresent()) {
            return null;
        }
        Person userReg = personMapper.toEntity(dto);
        userReg.setRole(dto.getRole());
        userReg.setPassword(passwordEncoder.encode(userReg.getPassword()));
        personRepository.save(userReg);
        return dto;
    }

    public PersonDto getByEmail(String email) {
        return personMapper.toDto(personRepository.findPersonByEmail(email).get());
    }
}
