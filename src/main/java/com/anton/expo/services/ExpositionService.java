package com.anton.expo.services;

import com.anton.expo.repository.entity.Exposition;

import java.time.LocalDate;
import java.util.List;

public interface ExpositionService {
    Exposition createExposition(String title, String description, double price, String imagePath,
                                LocalDate startDate, LocalDate endDate, long hallId);

    List<Exposition> getAllActiveExpositions();

    List<Exposition> getActiveExpositionsForHall(long id);

    Exposition getExpositionById(long id);

    long saveExposition(Exposition exposition);

    void updateExposition(Exposition exposition);

    List<Exposition> searchExpositionsByTitle(String query);
}
