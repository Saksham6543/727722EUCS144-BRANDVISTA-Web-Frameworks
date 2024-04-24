package com.example.brandvista.advertisement.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.brandvista.calendar.model.Event;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Advertisement {
    @Id
    private int advertisementId;
    private String advertisementTitle;
    private String advertisementDescription;
    private String advertisementImageUrl;
    private Date dateOfCreation = Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    @OneToOne(mappedBy = "advertisement", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Event event;
}
