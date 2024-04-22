package com.trademarket.services.repository;

import com.trademarket.services.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, UUID> {
}
