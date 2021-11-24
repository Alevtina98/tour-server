package ru.krista.tour.controller.domains.webApp.user.session.tour;

import ru.krista.tour.Dto;

import java.util.List;

public class TourService {
   // ProviderBase providerBase;
    public Dto<List<TourBo>> getAllTours () {
        return null;
    };
    public Dto<List<TourBo>> getGeneralTours (){
        return null;
    };
    public Dto<TourBo> getTour (Number id){
        return null;
    };
    public Dto<TourBo> createTour (TourBo data){
        return null;
    };
    public Dto<TourBo> changeTour (TourBo data){
        return null;
    };
    public Dto<Object> deleteTour (Number id){
        return null;
    };

    private String getCompliantName(String name){
        if (!name.equals(""))
            return name;
        return "Unnamed";
    }
}
