package com.trademarket.services.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trademarket.model.dto.PersonDto;
import com.trademarket.model.request.RabbitRequest;
import com.trademarket.model.response.RabbitResponse;
import com.trademarket.services.service.PersonService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@EnableRabbit
@Configuration
public class RabbitmqConfig {
    @Autowired
    private PersonService personService;
    @Autowired
    private ObjectMapper objectMapper;
    @RabbitListener(queues = "persons")
    public String personListen(String request) throws JsonProcessingException {
        RabbitResponse<PersonDto> rabbitResponse = new RabbitResponse<>();
        RabbitRequest rabbitRequest = objectMapper.readValue(request, RabbitRequest.class);
        if("save".equals(rabbitRequest.getTypeOperation())) {
            PersonDto body = objectMapper.readValue(rabbitRequest.getBody(), PersonDto.class);
            return objectMapper.writeValueAsString(personService.save(body));
        } else if ("getById".equals(rabbitRequest.getTypeOperation())){
            UUID body = UUID.fromString(rabbitRequest.getBody());
            return objectMapper.writeValueAsString(personService.getById(body));
        } else if ("getAll".equals(rabbitRequest.getTypeOperation())) {
            return objectMapper.writeValueAsString(personService.getAll());
        }
        return new RabbitResponse<PersonDto>("метод не найден", null).toString();
    }
}
