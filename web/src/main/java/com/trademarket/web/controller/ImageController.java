package com.trademarket.web.controller;

import com.trademarket.model.dto.PersonDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persons")
public class ImageController extends AbstractRestController<PersonDto> {
    public ImageController() {
        this.ROUTING_KEY = "images";
    }
}
