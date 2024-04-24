package com.example.brandvista.interaction.controller;

import java.sql.Date;
import java.time.LocalDate;
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

import com.example.brandvista.interaction.model.Interaction;
import com.example.brandvista.interaction.service.InteractionService;

@RestController
@RequestMapping("/interactions")
public class InteractionController {
    @Autowired
    private InteractionService interactionService;

    @PostMapping("/create")
    public String createNewUser(@RequestBody Interaction interaction) {
        interactionService.interUser(interaction);
        return "Interaction Logged Successfully.";
    }

    @PutMapping("/update/{interactionId}")
    public ResponseEntity<String> putInteraction(@PathVariable int interactionId,
            @RequestBody Interaction interaction) {
        Interaction inter = interactionService.findById(interactionId);
        if (inter != null) {
            inter.setDateOfInteraction(Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            inter.setInteractionType(interaction.getInteractionType());
            inter.setMonth(interaction.getMonth());
            inter.setYear(interaction.getYear());
            interactionService.interUser(inter);
            return new ResponseEntity<>("Views updated successfully", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Data not found", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/get/{interactionId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Interaction getPost(@PathVariable("interactionId") int interactionId) {
        return interactionService.findById(interactionId);
    }

    @GetMapping("/get")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Interaction> getInteractions() {
        return interactionService.getAllInteractions();
    }

    @GetMapping("/get/certain")
    @ResponseStatus(value=HttpStatus.OK)
    public List<Object> getInteractionIdAndType() {
        return interactionService.getInteractions();
    }

    @GetMapping("/get/count/{year}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public String getNumofInteractions(@PathVariable("year") int year) {
        return "Number of Interactions in the year " + year + " : "
                + String.valueOf(interactionService.findCountbyYear(year));
    }

    @GetMapping("/get/pageNation/{pn}/{sz}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<Interaction> getInteractions(@PathVariable("pn") int pn, @PathVariable("sz") int sz) {
        return interactionService.getPageNation(pn, sz);
    }

    @GetMapping("/get/countbw/{y1}/{y2}")
    @ResponseStatus(value = HttpStatus.OK)
    public String getNumofInteractionsRange(@PathVariable("y1") int y1, @PathVariable("y2") int y2) {
        return "Number of Interactions between the years " + y1 + " and " + y2 + " are : "
                + String.valueOf(interactionService.findCountBwYears(y1, y2));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletion(@PathVariable int id)
    {
        Interaction exist = interactionService.findById(id);
        if(exist != null){
            interactionService.deleteInteraction(id);
            return new ResponseEntity<>("Deleted the Interaction.", HttpStatus.NO_CONTENT);
        }
        else  
            return new ResponseEntity<>("Data not found", HttpStatus.NOT_FOUND);
    }
}