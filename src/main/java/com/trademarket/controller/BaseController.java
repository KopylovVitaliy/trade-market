package com.trademarket.controller;

import com.trademarket.entity.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @GetMapping
    public Person getSum(){
        Person.builder().surName("ss").build();
        return Person.builder()
                .email("hui")
                .name("VASYA")
                .surName("Petrov")
                .build();
    }
}
