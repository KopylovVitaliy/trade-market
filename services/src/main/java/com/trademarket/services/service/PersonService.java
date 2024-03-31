package com.trademarket.services.service;

import com.trademarket.model.dto.PersonDto;
import com.trademarket.services.entity.Person;
import com.trademarket.services.mapper.PersonMapper;
import com.trademarket.services.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService
        extends AbstractService<PersonRepository, PersonDto, Person, PersonMapper>{

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;
    @Override
    public PersonRepository getRepository(){
        return personRepository;
    }

    @Override
    public PersonMapper getMapper() {
        return personMapper;
    }


}
