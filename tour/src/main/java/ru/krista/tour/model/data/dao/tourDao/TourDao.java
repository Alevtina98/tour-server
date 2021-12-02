package ru.krista.tour.model.data.dao.tourDao;

import ru.krista.tour.Dto;
import ru.krista.tour.controller.domains.webApp.user.session.tour.TourBo;
import ru.krista.tour.controller.domains.webApp.user.session.tour.ITourDao;
import ru.krista.tour.model.data.dao.IProvider;
import ru.krista.tour.model.data.persistence.entities.Tour;

import java.util.List;

public class TourDao implements ITourDao {
    IProvider provider;

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
    public TourDao (IProvider provider) {
        this.provider = provider;
    }


    @Override
    public Dto<TourBo> createTour(Tour tour) {
        return null;
    }

    public Dto<TourBo> readTour(Long id){
        return null;
    };
    public Dto<TourBo> updateTour(Tour tour){
        return null;
    };
    public Dto<TourBo> deleteTour (Tour tour){
        return null;
    };

    public Dto<List<TourBo>> readAllTours(){
        return null;
    };
    public Dto<List<TourBo>> readToursFilterByGeneralFocus() {
        return null;
    };
}
