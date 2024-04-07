package com.trademarket.web.controller;

import com.trademarket.model.dto.PersonDto;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persons")
public class PersonController extends AbstractRestController<PersonDto> {
    public PersonController() {
        this.ROUTING_KEY = "persons";
    }

    @GetMapping(value = "/me")
    public String currentUserName(Authentication authentication) {
        return authentication.getName();
    }
}
