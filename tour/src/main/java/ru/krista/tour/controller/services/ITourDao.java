package ru.krista.tour.controller.services;

import ru.krista.tour.Dto;
import ru.krista.tour.model.data.persistence.entities.Tour;

import java.util.List;

public interface ITourDao {
    public Dto<Tour> createTour(Tour tour);
    public Dto<Tour> readTour(Number id);
    public Dto<Tour> updateTour(Tour tour);
    public Dto<Tour> deleteTour (Tour tour);

    public Dto<List<Tour>> readAllTours();
    public Dto<List<Tour>> readToursFilterByGeneralFocus();
}
