package com.trademarket.services.service;

import com.trademarket.model.dto.AdvertisementDto;
import com.trademarket.services.entity.Advertisement;
import com.trademarket.services.mapper.AdvertisementMapper;
import com.trademarket.services.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementService
        extends AbstractService<AdvertisementRepository, AdvertisementDto, Advertisement, AdvertisementMapper>{

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private AdvertisementMapper advertisementMapper;
    @Override
    public AdvertisementRepository getRepository(){
        return advertisementRepository;
    }

    @Override
    public AdvertisementMapper getMapper() {
        return advertisementMapper;
    }


}
