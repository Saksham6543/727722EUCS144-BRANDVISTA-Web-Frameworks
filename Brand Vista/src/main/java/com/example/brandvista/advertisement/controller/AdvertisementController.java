package com.example.brandvista.advertisement.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.brandvista.advertisement.model.Advertisement;
import com.example.brandvista.advertisement.service.AdvertisementService;

@RestController
@RequestMapping("/ads")
public class AdvertisementController {
    private AdvertisementService advertisementService;

    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String postAdvertisement(@RequestBody Advertisement advertisement) {
        advertisementService.createAdvertisement(advertisement);
        return "Advertisement Created Successfully";
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Advertisement getAdvertisement(@PathVariable int id) {
        return advertisementService.getAdvertisementById(id);
    }

    @GetMapping("/get")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Advertisement> getAdvertisements() {
        return advertisementService.getAllAdvertisement();
    }

    @GetMapping("/get/pageNation/{pn}/{sz}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<Advertisement> getInteractions(@PathVariable("pn") int pn, @PathVariable("sz") int sz) {
        return advertisementService.getPageNation(pn, sz);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> putAdvertisement(@PathVariable int id, @RequestBody Advertisement advertisement) {
        Advertisement exist = advertisementService.getAdvertisementById(id);
        if (exist != null) {
            exist.setAdvertisementTitle(advertisement.getAdvertisementTitle());
            exist.setAdvertisementDescription(advertisement.getAdvertisementDescription());
            exist.setAdvertisementImageUrl(advertisement.getAdvertisementImageUrl());
            exist.setDateOfCreation(Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            advertisementService.createAdvertisement(exist);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body ("Advertisement updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAdvertisement(@PathVariable int id) {
        Advertisement exist = advertisementService.getAdvertisementById(id);
        if (exist != null) {
            advertisementService.deleteAdvertisement(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body( "Advertisement deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not found");
        }
    }
}
