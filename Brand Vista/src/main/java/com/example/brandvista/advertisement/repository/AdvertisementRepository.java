package com.example.brandvista.advertisement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.brandvista.advertisement.model.Advertisement;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {
    Advertisement findByAdvertisementDescriptionEndingWith(String str);
}
