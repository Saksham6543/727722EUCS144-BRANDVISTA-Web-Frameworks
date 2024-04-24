package com.example.brandvista.calendar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.brandvista.calendar.model.Event;
import com.example.brandvista.calendar.repository.EventRepository;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public void createEvent(Event event) {
        eventRepository.save(event);
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public Event getEventbyId(int eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }

    public void deleteEvent(int eventId) {
        eventRepository.deleteById(eventId);
    }
}
