package ru.krista.tour.controller.domains.webApp.user.session.tour;

import ru.krista.tour.Dto;
import ru.krista.tour.model.data.dataObjects.TourDo;

import java.util.List;

public interface ITourDao {
    public Dto<TourDo> createTour(TourDo tourDo);
    public Dto<TourDo> readTour(Long id);
    public Dto<TourDo> updateTour(TourDo tourDo);
    public Dto<TourDo> deleteTour (Long id);

    public Dto<List<TourDo>> readAllTours();
    public Dto<List<TourDo>> readToursFilterByGeneralFocus();
}
