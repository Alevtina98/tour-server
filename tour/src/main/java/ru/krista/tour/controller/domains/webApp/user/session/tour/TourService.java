package ru.krista.tour.controller.domains.webApp.user.session.tour;

import ru.krista.tour.Dto;
import ru.krista.tour.model.data.persistence.entities.Tour;

import java.util.List;

public class TourService {
    ITourDao dao;
    private Tour convertTourBo (TourBo tourBo) {
        Tour tourDo = new Tour();
        tourDo.setName(tourBo.name);
        tourDo.setDesc(tourBo.desc);
        tourDo.setCode(tourBo.learningProgram.codeXml);
        tourDo.setCodeJS(tourBo.learningProgram.codeJs);
        tourDo.setGeneralUser(tourBo.learningProgram.focus.isGeneralUser);
        tourDo.setFormName(tourBo.learningProgram.focus.target.formName);
        tourDo.setFormCaption(tourBo.learningProgram.focus.target.formCaption);
        return tourDo;
    }
    private TourBo convertTourDo (Tour tourDo) {
        TourBo tourBo = new TourBo();
        tourBo.id = tourDo.getId();
        tourBo.name = tourDo.getName();
        tourBo.desc = tourDo.getDesc();
        tourBo.learningProgram.codeXml = tourDo.getCode();
        tourBo.learningProgram.codeJs = tourDo.getCodeJS();
        tourBo.learningProgram.focus.isGeneralUser = tourDo.getIsGeneral();
        tourBo.learningProgram.focus.target.formName =tourDo.getFormName();
        tourBo.learningProgram.focus.target.formCaption = tourDo.getFormCaption();
        return tourBo;
    }
    // ProviderBase providerBase;
    public TourService(ITourDao dao) {
        this.dao = dao;
    }

    public Dto<List<TourBo>> getAllTours() {
        return null;
    }

    ;

    public Dto<List<TourBo>> getGeneralTours() {
        return null;
    }

    ;

    public Dto<TourBo> getTour(Number id) {
        return null;
    }

    ;

    public Dto<TourBo> createTour(TourBo data) {
        return null;
    }

    ;

    public Dto<TourBo> changeTour(TourBo data) {
        return null;
    }

    ;

    public Dto<Object> deleteTour(Number id) {
        return null;
    }

    ;

    private String getCompliantName(String name) {
        if (!name.equals(""))
            return name;
        return "Unnamed";
    }
}
