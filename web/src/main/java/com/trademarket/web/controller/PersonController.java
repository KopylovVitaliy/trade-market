package com.trademarket.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trademarket.model.dto.PersonDto;
import com.trademarket.model.request.RabbitRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public String getAll () throws JsonProcessingException {
        RabbitRequest rabbitRequest = new RabbitRequest("getAll", null);
        String request = objectMapper.writeValueAsString(rabbitRequest);
        return (String) rabbitTemplate.convertSendAndReceive("persons", request);
    }

    @PostMapping
    public String add(@RequestBody PersonDto person) throws JsonProcessingException {
        String personDto = objectMapper.writeValueAsString(person);
        RabbitRequest rabbitRequest = new RabbitRequest("save", personDto);
        String request = objectMapper.writeValueAsString(rabbitRequest);
        return (String) rabbitTemplate.convertSendAndReceive("persons", request);
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable UUID id) throws JsonProcessingException {
        RabbitRequest rabbitRequest = new RabbitRequest("getById", id.toString());
        String request = objectMapper.writeValueAsString(rabbitRequest);
        return (String) rabbitTemplate.convertSendAndReceive("persons", request);
    }
}
