package ru.krista.tour.controller.domains.webApp.user.session.tour;

import ru.krista.tour.Dto;
import ru.krista.tour.model.data.persistence.entities.Tour;

import java.util.List;

public interface ITourDao {
    public Dto<TourBo> createTour(Tour tour);
    public Dto<TourBo> readTour(Long id);
    public Dto<TourBo> updateTour(Tour tour);
    public Dto<TourBo> deleteTour (Tour tour);

    public Dto<List<TourBo>> readAllTours();
    public Dto<List<TourBo>> readToursFilterByGeneralFocus();
}
