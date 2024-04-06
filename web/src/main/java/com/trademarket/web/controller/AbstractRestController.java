package com.trademarket.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trademarket.model.request.RabbitRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


public class AbstractRestController <D> {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    protected String ROUTING_KEY;
    @GetMapping
    public String getAll () throws JsonProcessingException {
        RabbitRequest rabbitRequest = new RabbitRequest("getAll", null);
        String request = objectMapper.writeValueAsString(rabbitRequest);
        return (String) rabbitTemplate.convertSendAndReceive(ROUTING_KEY, request);
    }

    @PostMapping
    public String add(@RequestBody D person) throws JsonProcessingException {
        String D = objectMapper.writeValueAsString(person);
        RabbitRequest rabbitRequest = new RabbitRequest("save", D);
        String request = objectMapper.writeValueAsString(rabbitRequest);
        return (String) rabbitTemplate.convertSendAndReceive(ROUTING_KEY, request);
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable UUID id) throws JsonProcessingException {
        RabbitRequest rabbitRequest = new RabbitRequest("getById", id.toString());
        String request = objectMapper.writeValueAsString(rabbitRequest);
        return (String) rabbitTemplate.convertSendAndReceive(ROUTING_KEY, request);
    }

    @PutMapping("/{id}")
    public String edit(@RequestBody D person, @PathVariable UUID id) throws JsonProcessingException {
        String D = objectMapper.writeValueAsString(person);
        RabbitRequest rabbitRequest = new RabbitRequest("edit", D);
        String request = objectMapper.writeValueAsString(rabbitRequest);
        return (String) rabbitTemplate.convertSendAndReceive(ROUTING_KEY, request);
    }
}
