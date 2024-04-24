package com.example.brandvista.interaction.service;

import java.util.List;
import java.lang.Object;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.brandvista.interaction.model.Interaction;
import com.example.brandvista.interaction.repository.InteractionRepository;

@Service
public class InteractionService {
    @Autowired
    private InteractionRepository interactionRepository;

    public Interaction interUser(Interaction inter) {
        return interactionRepository.save(inter);
    }

    public Interaction findById(int InteractionId) {
        return interactionRepository.findById(InteractionId).orElse(null);
    }

    public int findCountbyYear(int year) {
        return interactionRepository.findByYear(year);
    }

    public int findCountBwYears(int y1, int y2) {
        return interactionRepository.findCountYearRange(y1, y2);
    }

    public List<Interaction> getPageNation(int pn, int sz) {
        return interactionRepository.findAll(PageRequest.of(pn, sz, Sort.by(Sort.Direction.ASC, "month"))).getContent();
    }

    public List<Interaction> getAllInteractions() {
        return interactionRepository.findAll();
    }

    public List<Object> getInteractions() {
        return interactionRepository.findAllInteractions();
    }

    public void deleteInteraction(int id)
    {
        interactionRepository.deleteById(id);
    }
}
