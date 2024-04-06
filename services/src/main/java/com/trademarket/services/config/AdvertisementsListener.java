package com.trademarket.services.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trademarket.model.dto.AdvertisementDto;
import com.trademarket.services.entity.Advertisement;
import com.trademarket.services.mapper.AdvertisementMapper;
import com.trademarket.services.repository.AdvertisementRepository;
import com.trademarket.services.service.AdvertisementService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdvertisementsListener extends AbstractListener<AdvertisementDto, AdvertisementRepository,
        Advertisement, AdvertisementMapper, AdvertisementService>{
    @Autowired
    private AdvertisementService advertisementService;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected AdvertisementService getService() {
        return advertisementService;
    }

    @Override
    protected ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    @Override
    @RabbitListener(queues = "advertisements")
    public String listen(String request) throws JsonProcessingException {
        return commonListen(request);
    }
}
