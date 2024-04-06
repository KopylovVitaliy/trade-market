package com.trademarket.web.controller;

import com.trademarket.model.dto.AdvertisementDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/advertisements")
public class AdvertisementController extends AbstractRestController<AdvertisementDto>{
    public AdvertisementController (){
        this.ROUTING_KEY = "advertisements";
    }
}
