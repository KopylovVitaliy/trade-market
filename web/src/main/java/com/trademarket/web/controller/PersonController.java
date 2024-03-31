package com.trademarket.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trademarket.model.dto.PersonDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public List<PersonDto> getAll (){
        return new ArrayList<PersonDto>();
    }

    @PostMapping
    public String add(@RequestBody PersonDto person) throws JsonProcessingException {
        String request = objectMapper.writeValueAsString(person);
        return (String) rabbitTemplate.convertSendAndReceive("persons", request);
    }
}
