package com.example.brandvista.interaction.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.brandvista.socialposts.model.Post;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Interaction {
	@Id
	private int interactionId;
	private String interactionType;
	private Date dateOfInteraction = Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	private int month;
	private int year;
	@ManyToOne
	@JsonBackReference
	private Post post;
}
