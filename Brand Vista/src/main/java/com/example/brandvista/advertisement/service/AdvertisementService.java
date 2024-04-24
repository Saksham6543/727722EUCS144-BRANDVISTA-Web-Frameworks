package com.example.brandvista.advertisement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.brandvista.advertisement.model.Advertisement;
import com.example.brandvista.advertisement.repository.AdvertisementRepository;

@Service
public class AdvertisementService {
    @Autowired
    private AdvertisementRepository advertisementRepository;

    public Advertisement createAdvertisement(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }

    public Advertisement getAdvertisementById(int id) {
        return advertisementRepository.findById(id).orElse(null);
    }

    public List<Advertisement> getAllAdvertisement() {
        return (List<Advertisement>) advertisementRepository.findAll();
    }

    public List<Advertisement> getPageNation(int pn, int sz) {
        return advertisementRepository.findAll(PageRequest.of(pn, sz)).getContent();
    }

    public void deleteAdvertisement(int id) {
        advertisementRepository.deleteById(id);
    }
}
