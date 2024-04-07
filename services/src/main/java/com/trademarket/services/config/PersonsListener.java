package com.trademarket.services.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trademarket.model.dto.PersonDto;
import com.trademarket.model.request.RabbitRequest;
import com.trademarket.model.response.RabbitResponse;
import com.trademarket.services.entity.Person;
import com.trademarket.services.mapper.PersonMapper;
import com.trademarket.services.repository.PersonRepository;
import com.trademarket.services.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class PersonsListener extends AbstractListener<PersonDto, PersonRepository,
        Person, PersonMapper, PersonService> {
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
    protected Class<PersonDto> getDtoClass() {
        return PersonDto.class;
    }

    @Override
    @RabbitListener(queues = "persons")
    public String listen(String request) {
        return commonListen(request);
    }

    @Override
    public RabbitResponse customListen(RabbitRequest rabbitRequest) {
        String body = rabbitRequest.getBody();
        try {
            return new RabbitResponse(getObjectMapper().writeValueAsString(getService().getByEmail(body)));
        } catch (JsonProcessingException e) {
            log.error("Некорректное тело запроса ", e);
            return new RabbitResponse("Некорректное тело запроса ", rabbitRequest.getBody());
        } catch (Exception e) {
            log.error("Ошибка hibernate ", e);
            return new RabbitResponse("Ошибка hibernate ", rabbitRequest.getBody());
        }

    }
}
