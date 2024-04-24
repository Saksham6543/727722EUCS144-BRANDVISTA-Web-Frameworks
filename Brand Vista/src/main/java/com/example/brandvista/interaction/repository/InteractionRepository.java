package com.example.brandvista.interaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.brandvista.interaction.model.Interaction;
import java.util.List;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Integer> {
    @Query("SELECT COUNT(I) FROM Interaction I WHERE year = ?1")
    int findByYear(int year);

    @Query("SELECT COUNT(I) FROM Interaction I WHERE year >= ?1 AND year <= ?2")
    int findCountYearRange(int y1, int y2);

    @Query("SELECT I.interactionId, I.interactionType, I.dateOfInteraction FROM Interaction I")
    List<Object> findAllInteractions();
}
 