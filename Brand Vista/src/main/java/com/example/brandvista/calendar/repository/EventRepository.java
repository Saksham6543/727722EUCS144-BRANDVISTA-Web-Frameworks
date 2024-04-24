package com.example.brandvista.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.brandvista.calendar.model.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

}
