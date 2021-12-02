package ru.krista.tour.controller.domains.webApp.user.session;

import ru.krista.tour.Dto;
import ru.krista.tour.controller.domains.webApp.user.session.tour.TourBo;

import java.util.List;

public interface ITourService {
    public Dto<List<TourBo>> getToursFilterByGeneralFocus();
    public Dto<List<TourBo>> getTours();
    public Dto<TourBo> getTour (Number id);
    public Dto<TourBo> createTour(TourBo tour);
    public Dto<TourBo> changeTour(TourBo tour);
    public Dto<TourBo> deleteTour (TourBo tour);
}
