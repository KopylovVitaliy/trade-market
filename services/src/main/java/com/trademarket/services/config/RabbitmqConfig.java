package com.trademarket.services.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trademarket.model.dto.PersonDto;
import com.trademarket.services.service.PersonService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitmqConfig {
    @Autowired
    private PersonService personService;
    @Autowired
    private ObjectMapper objectMapper;
    @RabbitListener(queues = "persons")
    public String personListen(String request) throws JsonProcessingException {
        PersonDto dto = objectMapper.readValue(request, PersonDto.class);
        return objectMapper.writeValueAsString(personService.save(dto));
    }
}
