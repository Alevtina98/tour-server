package ru.krista.tour.model.data.dao.tourDao;

import ru.krista.tour.Dto;
import ru.krista.tour.controller.domains.webApp.user.session.tour.ITourDao;
import ru.krista.tour.model.data.dataObjects.TourDo;
import ru.krista.tour.model.data.dao.IProvider;
import ru.krista.tour.model.data.dao.tourDao.queryUtils.FilterByGeneral;
import ru.krista.tour.model.data.dao.tourDao.queryUtils.SelectAllFromTour;
import ru.krista.tour.model.data.persistence.entities.Tour;

import java.util.List;
import java.util.stream.Collectors;


/*
 * предоставляет сервису домена Тур доступ к персистентным данным
 * */

public class TourDao implements ITourDao {
    IProvider provider;

    public TourDao(IProvider provider) {
        this.provider = provider;
    }

    public static Tour convertTourDo(TourDo tourDo) {
        Tour tourEntity = new Tour();
        tourEntity.setId(tourDo.id);
        tourEntity.setName(tourDo.name);
        tourEntity.setDesc(tourDo.desc);
        tourEntity.setCode(tourDo.codeXml);
        tourEntity.setCodeJS(tourDo.codeJs);
        tourEntity.setGeneralUser(tourDo.isGeneralUser);
        tourEntity.setFormName(tourDo.formName);
        tourEntity.setFormCaption(tourDo.formCaption);
        return tourEntity;
    }

    public static TourDo convertTourEntity(Tour tourEntity) {
        TourDo tourDo = new TourDo();
        tourDo.id = tourEntity.getId();
        tourDo.name = tourEntity.getName();
        tourDo.desc = tourEntity.getDesc();
        tourDo.codeXml = tourEntity.getCode();
        tourDo.codeJs = tourEntity.getCodeJS();
        tourDo.isGeneralUser = tourEntity.getIsGeneral();
        tourDo.formName = tourEntity.getFormName();
        tourDo.formCaption = tourEntity.getFormCaption();
        tourDo.dateCreate = tourEntity.getDateCreate();
        tourDo.dateChange = tourEntity.getDateChange();
        return tourDo;
    }

    public static List<TourDo> convertTourEntity(List<Tour> tourEntityList) {
        return tourEntityList.stream().map(TourDao::convertTourEntity).collect(Collectors.toList());
    }

    @Override
    public Dto<TourDo> createTour(TourDo tourDo) {
        Tour tourEntity = convertTourDo(tourDo);
        Dto<Tour> providerResult = provider.create(tourEntity);
        Dto<TourDo> result = new Dto<>(null);
        if (providerResult.status == Dto.Status.ok) {
            result.setData(convertTourEntity(providerResult.data));
        } else {
            result.setError("TourDao: Не удалось сохранить данные о туре");
            result.addErrorMsg(providerResult.errorMsgList);
        }
        return result;
    }

    public Dto<TourDo> readTour(Long id) {
        Dto<Tour> providerResult = provider.readById(Tour.class, id);
        Dto<TourDo> result = new Dto<>(null);
        if (providerResult.status == Dto.Status.ok) {
            result.setData(convertTourEntity(providerResult.data));
        } else {
            result.setError("TourDao: Не удалось прочитать данные о туре");
            result.addErrorMsg(providerResult.errorMsgList);
        }
        return result;
    }

    public Dto<TourDo> updateTour(TourDo tourDo) {
        Tour tourEntity = convertTourDo(tourDo);
        Dto<Tour> providerResult = provider.update(tourEntity);
        Dto<TourDo> result = new Dto<>(null);
        if (providerResult.status == Dto.Status.ok) {
            result.setData(convertTourEntity(providerResult.data));
        } else {
            result.setError("TourDao: Не удалось обновить данные о туре");
            result.addErrorMsg(providerResult.errorMsgList);
        }
        return result;
    }

    public Dto<TourDo> deleteTour(Long id) {
        Dto<Tour> providerResult = provider.delete(Tour.class, id);
        Dto<TourDo> result = new Dto<>(null);
        if (providerResult.status == Dto.Status.ok) {
            result.setData(convertTourEntity(providerResult.data));
        } else {
            result.setError("TourDao: Не удалось удалить данные о туре");
            result.addErrorMsg(providerResult.errorMsgList);
        }
        return result;
    }

    public Dto<List<TourDo>> readAllTours() {
        Dto<List<Tour>> providerResult = provider.readAll(Tour.class);
        Dto<List<TourDo>> result = new Dto<>(null);
        if (providerResult.status == Dto.Status.ok) {
            result.setData(convertTourEntity(providerResult.data));
        } else {
            result.setError("TourDao: Не удалось прочитать данные обо всех турах");
            result.addErrorMsg(providerResult.errorMsgList);
        }
        return result;
    }

    public Dto<List<TourDo>> readToursFilterByGeneralFocus() {
        Dto<List<Tour>> providerResult = provider.readByFlagFilter(new SelectAllFromTour(), new FilterByGeneral());
        Dto<List<TourDo>> result = new Dto<>(null);
        if (providerResult.status == Dto.Status.ok) {
            result.setData(convertTourEntity(providerResult.data));
        } else {
            result.setError("TourDao: Не удалось прочитать данные об общепользовательских турах");
            result.addErrorMsg(providerResult.errorMsgList);
        }
        return result;
    }
}
