package ru.krista.tour.model.data.dao.tourDao;

import ru.krista.tour.Dto;
import ru.krista.tour.controller.domains.webApp.user.session.tour.TourBo;
import ru.krista.tour.controller.services.ITourDao;
import ru.krista.tour.model.data.dao.IProvider;
import ru.krista.tour.model.data.persistence.entities.Tour;

import java.util.List;

public class TourDao implements ITourDao {
    IProvider provider;

    public TourDao (IProvider provider) {
        this.provider = provider;
    }

    public Dto<Tour> createTour(Tour tour){
        return null;
    };
    public Dto<Tour> readTour(Long id){
        return null;
    };
    public Dto<Tour> updateTour(Tour tour){
        return null;
    };
    public Dto<Tour> deleteTour (Tour tour){
        return null;
    };

    public Dto<List<Tour>> readAllTours(){
        return null;
    };
    public Dto<List<Tour>> readToursFilterByGeneralFocus() {
        return null;
    };
}
