package com.trademarket.services.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trademarket.model.dto.PersonDto;
import com.trademarket.services.entity.Person;
import com.trademarket.services.mapper.PersonMapper;
import com.trademarket.services.repository.PersonRepository;
import com.trademarket.services.service.PersonService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonsListener extends AbstractListener<PersonDto, PersonRepository,
        Person, PersonMapper, PersonService>{
    @Autowired
    private PersonService personService;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected PersonService getService() {
        return personService;
    }

    @Override
    protected ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    @Override
    @RabbitListener(queues = "persons")
    public String listen(String request) throws JsonProcessingException {
        return commonListen(request);
    }
}
