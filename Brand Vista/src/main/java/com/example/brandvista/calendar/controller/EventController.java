package com.example.brandvista.calendar.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.brandvista.advertisement.repository.AdvertisementRepository;
import com.example.brandvista.advertisement.service.AdvertisementService;
import com.example.brandvista.calendar.model.Event;
import com.example.brandvista.calendar.service.EventService;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService EventService;
    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private AdvertisementService advertisementService;

    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createNewEvent(@RequestBody Event event) {
        Advertisement adv = event.getAdvertisement();
        event.setAdvertisement(advertisementService.getAdvertisementById(adv.getAdvertisementId()));
        EventService.createEvent(event);
        return "Event Created Successfully.";
    }

    @GetMapping("/get")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Event> getEvents() {
        return EventService.getEvents();
    }

    @PostMapping("/descp")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String postMethodName(@RequestBody Event event) {
        Advertisement ad = event.getAdvertisement();
        Advertisement adj = advertisementRepository.findByAdvertisementDescriptionEndingWith(ad.getAdvertisementDescription());
        event.setAdvertisement(adj);
        EventService.createEvent(event);
        return "Event Created Successfully.";
    }

    @GetMapping("/get/{eventId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Event getEvent(@PathVariable("eventId") int eventId) {
        System.out.println(EventService.getEventbyId(eventId).getAdvertisement().getAdvertisementId());
        return EventService.getEventbyId(eventId);
    }

    @PutMapping("/update/{eventId}")
    public ResponseEntity<String> putMethodName(@PathVariable("eventId") int eventId, @RequestBody Event updatedEvent) {
        Event existingEvent = EventService.getEventbyId(eventId);
        if (existingEvent != null) {
            existingEvent.setEventTitle(updatedEvent.getEventTitle());
            existingEvent.setEventDescription(updatedEvent.getEventDescription());
            existingEvent.setPostId(updatedEvent.getPostId());
            existingEvent.setEventDate(Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            existingEvent.setEventTime(Time.valueOf(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
            EventService.createEvent(existingEvent);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Event Updated Successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Given Event-ID doesnot exist. Please Try Again.");
    }

    @DeleteMapping("/delete/{eventId}")
    public ResponseEntity<String> deleteQuery(@PathVariable("eventId") int eventId) {
        if (EventService.getEventbyId(eventId) != null) {
            EventService.deleteEvent(eventId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body( "Event Deleted Successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( "Event Not Found.");
    }
}