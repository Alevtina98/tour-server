package ru.krista.tour.controller.domains.webApp.user.session.tour;

import ru.krista.tour.Dto;
import ru.krista.tour.model.data.dataObjects.TourDo;

import javax.ws.rs.NotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TourService {
    ITourDao dao;


    public TourService(ITourDao dao) {
        this.dao = dao;
    }

    public static TourDo convertTourBo(TourBo tourBo) {
        TourDo tourDo = new TourDo();
        tourDo.id = tourBo.id;
        tourDo.name = getCompliantName(tourBo.name);
        tourDo.desc = tourBo.desc;
        tourDo.codeXml = tourBo.learningProgram.codeXml;
        tourDo.codeJs = tourBo.learningProgram.codeJs;
        tourDo.isGeneralUser = tourBo.learningProgram.focus.isGeneralUser;
        tourDo.formName = tourBo.learningProgram.focus.target.formName;
        tourDo.formCaption = tourBo.learningProgram.focus.target.formCaption;
        return tourDo;
    }

    public static TourBo convertTourDo(TourDo tourDo) {
        TourBo tourBo = new TourBo();
        tourBo.id = tourDo.id;
        tourBo.name = tourDo.name;
        tourBo.desc = tourDo.desc;
        tourBo.dateCreate = tourDo.dateCreate;
        tourBo.dateChange = tourDo.dateChange;
        tourBo.learningProgram.codeXml = tourDo.codeXml;
        tourBo.learningProgram.codeJs = tourDo.codeJs;
        tourBo.learningProgram.focus.isGeneralUser = tourDo.isGeneralUser;
        tourBo.learningProgram.focus.target.formName = tourDo.formName;
        tourBo.learningProgram.focus.target.formCaption = tourDo.formCaption;
        return tourBo;
    }
    public static List<TourBo> convertTourDo(List<TourDo> tourDoList) {
        return tourDoList.stream().map(TourService::convertTourDo).collect(Collectors.toList());
    }
    private static String getCompliantName(String name) {
        if (!name.equals(""))
            return name;
        return "Unnamed";
    }

    public Dto<TourBo> createTour(TourBo tourBo) {
        Dto<TourDo> daoDto = dao.createTour(convertTourBo(tourBo));
        Dto<TourBo> result =  new Dto<>(null);
        if (daoDto.status == Dto.Status.error) {
            result.setError("TourService: Не удалось создать тур");
            result.addErrorMsg(daoDto.errorMsgList);
        }
        System.out.println(daoDto.data);
        result.setData(convertTourDo(daoDto.data));
        return result;
    }
    public Dto<TourBo> changeTour(TourBo tourBo) {
        Dto<TourDo> daoDto = dao.updateTour(convertTourBo(tourBo));
        Dto<TourBo> result =  new Dto<>(null);
        if (daoDto.status == Dto.Status.error) {
            result.setError("TourService: Не удалось изменить тур");
            result.addErrorMsg(daoDto.errorMsgList);
        }
        result.setData(convertTourDo(daoDto.data));
        return result;
    }

    public Dto<List<TourBo>> getAllTours() {
        Dto<List<TourDo>> daoDto = dao.readAllTours();
        Dto<List<TourBo>> result =  new Dto<>(null);
        if (daoDto.status == Dto.Status.error) {
            result.setError("TourService: Не удалось получить список всех туров");
            result.addErrorMsg(daoDto.errorMsgList);
        }
        result.setData(convertTourDo(daoDto.data));
        //throw new NotFoundException();
        return result;
    }

    public Dto<List<TourBo>> getGeneralTours() {
        Dto<List<TourDo>> daoDto = dao.readToursFilterByGeneralFocus();
        Dto<List<TourBo>> result =  new Dto<>(null);
        if (daoDto.status == Dto.Status.error) {
            result.setError("TourService: Не удалось получить список общепользовательских туров");
            result.addErrorMsg(daoDto.errorMsgList);
        }
        result.setData(convertTourDo(daoDto.data));
        return result;
    }

    public Dto<TourBo> getTour(Long id) {
        Dto<TourDo> daoDto = dao.readTour(id);
        Dto<TourBo> result =  new Dto<>(null);
        if (daoDto.status == Dto.Status.error) {
            result.setError("TourService: Не удалось получить тур");
            result.addErrorMsg(daoDto.errorMsgList);
        }
        result.setData(convertTourDo(daoDto.data));
        return result;
    }




    public Dto<TourBo> deleteTour(Long id) {
        Dto<TourDo> daoDto = dao.deleteTour(id);
        Dto<TourBo> result =  new Dto<>(null);
        if (daoDto.status == Dto.Status.error) {
            result.setError("TourService: Не удалось удалить тур");
            result.addErrorMsg(daoDto.errorMsgList);
        }
        result.setData(convertTourDo(daoDto.data));
        return result;
    }


}
