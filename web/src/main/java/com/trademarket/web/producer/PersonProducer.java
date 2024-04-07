package com.trademarket.web.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trademarket.model.dto.PersonDto;
import com.trademarket.model.request.RabbitRequest;
import com.trademarket.model.response.RabbitResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class PersonProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    private final static String ROUTING_KEY = "persons";

    public PersonDto getByEmail(String email) {
        RabbitRequest rabbitRequest = new RabbitRequest("getByEmail", email);
        String request;
        try {
            request = objectMapper.writeValueAsString(rabbitRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        PersonDto personDto = null;
        try {
            String userDetails = (String) (rabbitTemplate.convertSendAndReceive(ROUTING_KEY, request));
            RabbitResponse rabbitResponse = objectMapper.readValue(userDetails, RabbitResponse.class);
            if (rabbitResponse.getErrorMessage() != null) {
                log.error("ошибка: " + rabbitResponse.getErrorMessage());
                throw new RuntimeException(rabbitResponse.getErrorMessage());
            }
            personDto = objectMapper.readValue(rabbitResponse.getBody(), PersonDto.class);
        } catch (JsonProcessingException e) {
            log.error("некорректное тело ответа", e);
            throw new RuntimeException();
        }

        return personDto;
    }
}
