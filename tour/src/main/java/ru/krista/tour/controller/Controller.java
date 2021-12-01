package ru.krista.tour.controller;

import ru.krista.tour.Dto;
import ru.krista.tour.controller.domains.IModal;
import ru.krista.tour.controller.domains.webApp.user.UserService;
import ru.krista.tour.controller.domains.webApp.user.session.SessionBo;
import ru.krista.tour.model.Model;
import ru.krista.tour.view.resources.IController;
import ru.krista.tour.controller.domains.webApp.user.session.tour.TourBo;
import ru.krista.tour.controller.domains.webApp.user.session.tour.TourService;
import ru.krista.tour.controller.domains.webApp.user.session.tour.program.LearningProgramBo;
import ru.krista.tour.controller.domains.webApp.user.session.tour.program.focus.FocusBo;
import ru.krista.tour.controller.domains.webApp.user.session.tour.program.focus.target.TargetBo;
import ru.krista.tour.view.resources.presentations.informationObjects.*;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;



/*
* Работает с моделью - открывает и закрывает область персистенции
*
* Работает с сервисами:
*
* - предоставляет сервисам доступ к данным
* - передает сервисам клиентскую информацию
* - оценивает результаты работы сервисов
*
* Работает с представлением - распаковывает и упаковывает клиентскую информацию
* */

public class Controller implements IController {
    @Inject
    IModal modal;

    private void openPersistence() {
        modal = new Model();
        modal.openGateway();
    }
    private void closePersistence() {
        modal.closeGateway();
    }


    /*
    * Перенесение данных из информационных объектов в домены
    * */

    private TargetBo convertTargetIo(TourIo tourIo) {
        return new TargetBo(tourIo.formName, tourIo.formCaption);
    }
    private FocusBo convertFocusIo(TourIo tourIo) {
        return new FocusBo(tourIo.isGeneralUser, convertTargetIo(tourIo));
    }
    private LearningProgramBo convertLearningProgramIo(TourIo tourIo) {
        return new LearningProgramBo(tourIo.code, tourIo.codeJS, convertFocusIo(tourIo));
    }
    private TourBo convertTourIo(TourIo tourIo) {
        TourBo tourBo = new TourBo();
        tourBo.id = tourIo.id;
        tourBo.name = tourIo.name;
        tourBo.desc = tourIo.desc;
        tourBo.dateCreate = tourIo.dateCreate;
        tourBo.dateChange = tourIo.dateChange;
        tourBo.learningProgram = convertLearningProgramIo(tourIo);
        return tourBo;
    }
    private SessionBo convertUserSessionIo(UserSessionIo userSessionIo) {
        SessionBo sessionBo = new SessionBo();
         sessionBo.tour = convertTourIo(userSessionIo.tour);
         sessionBo.dateChange = userSessionIo.dateChange;
         sessionBo.status = userSessionIo.status;
        return sessionBo;
    }

    /*
     * Перенесение данных из доменов в информационные объекты
     * */

    private TourIo convertTourBo(TourBo tourBo) {
        TourIo tourIo = new TourIo();
        tourIo.id = tourBo.id;
        tourIo.name = tourBo.name;
        tourIo.desc = tourBo.desc;
        tourIo.code = tourBo.learningProgram.codeXml;
        tourIo.codeJS = tourBo.learningProgram.codeJs;
        tourIo.isGeneralUser = tourBo.learningProgram.focus.isGeneralUser;
        if (tourBo.learningProgram.focus.target != null) {
            tourIo.formName = tourBo.learningProgram.focus.target.formName;
            tourIo.formCaption = tourBo.learningProgram.focus.target.formCaption;
        }
        return tourIo;
    }
    private SessionIo convertSessionBo(SessionBo sessionBo) {
        SessionIo sessionIo = new SessionIo();
        sessionIo.tour = convertTourBo(sessionBo.tour);
        sessionIo.id = sessionBo.id;
        sessionIo.dateChange = sessionBo.dateChange;
        sessionIo.status = sessionBo.status;
        return null;
    }

    /*
     * Упаковка готовых доменов в DTO
     * */

    private Dto<List<TourIo>> wrapTourList(List<TourBo> tourBoList) {
        List<TourIo> tourIoList = tourBoList.stream().map(this::convertTourBo).collect(Collectors.toList());
        return new Dto<>(tourIoList);
    }
    private Dto<TourIo> wrapTour(TourBo tourBo) {
        TourIo tourIo = convertTourBo(tourBo);
        return new Dto<>(tourIo);
    }
    private Dto<List<SessionIo>> wrapSessionList(List<SessionBo> sessionBoList) {
        List<SessionIo> sessionIoList = sessionBoList.stream().map(this::convertSessionBo).collect(Collectors.toList());
        return new Dto<>(sessionIoList);
    }
    private Dto<SessionIo> wrapUserSession(SessionBo sessionBo) {
        SessionIo sessionIo = convertSessionBo(sessionBo);
        return new Dto<>(sessionIo);
    }


    /*
     * Работа с сервисом, отвечающим за туры
     * */

    @Override
    public Dto<TourIo> createTour(TourIo info) {

        openPersistence();
        TourService tourService = new TourService(modal.getTouDao());
        Dto<TourBo> tourServiceDto = tourService.createTour(convertTourIo(info));
        closePersistence();

        Dto<TourIo> result =  wrapTour(tourServiceDto.data);
        if (tourServiceDto.status == Dto.Status.error) {
            result.setError("Controller: Ошибка при создании тура");
            tourServiceDto.errorMsgList.forEach(result::addErrorMsg);
        }
        return result;
    }

    @Override
    public Dto<TourIo> changeTour(TourIo info) {

        openPersistence();
        TourService tourService = new TourService(modal.getTouDao());
        Dto<TourBo> tourServiceDto = tourService.changeTour(convertTourIo(info));
        closePersistence();

        Dto<TourIo> result =  wrapTour(tourServiceDto.data);
        if (tourServiceDto.status == Dto.Status.error) {
            result.setError("Controller: Ошибка при изменении тура");
            tourServiceDto.errorMsgList.forEach(result::addErrorMsg);
        }
        return result;
    }

    @Override
    public Dto<EmptyIo> deleteTour(TourIdIo info) {

        openPersistence();
        TourService tourService = new TourService(modal.getTouDao());
        Dto<Object> tourServiceDto = tourService.deleteTour(info.id);
        closePersistence();

        Dto<EmptyIo> result =  new Dto<>(null);
        if (tourServiceDto.status == Dto.Status.error) {
            result.setError("Controller: Ошибка при удалении тура");
            tourServiceDto.errorMsgList.forEach(result::addErrorMsg);
        }
        return result;
    }

    @Override
    public Dto<TourIo> getTour(TourIdIo info) {

        openPersistence();
        TourService tourService = new TourService(modal.getTouDao());
        Dto<TourBo> tourServiceDto = tourService.getTour(info.id);
        closePersistence();

        Dto<TourIo> result =  wrapTour(tourServiceDto.data);
        if (tourServiceDto.status == Dto.Status.error) {
            result.setError("Controller: Ошибка при получении тура");
            tourServiceDto.errorMsgList.forEach(result::addErrorMsg);
        }
        return result;
    }

    @Override
    public Dto<List<TourIo>> getAllTours() {

        openPersistence();
        TourService tourService = new TourService(modal.getTouDao());
        Dto<List<TourBo>> tourServiceDto = tourService.getAllTours();
        closePersistence();

        Dto<List<TourIo>> result = wrapTourList(tourServiceDto.data);
        if (tourServiceDto.status == Dto.Status.error) {
            result.setError("Controller: Ошибка при получении списка всех туров");
            tourServiceDto.errorMsgList.forEach(result::addErrorMsg);
        }
        return result;
    }

    @Override
    public Dto<List<TourIo>> getToursWithGeneralFocus() {

        openPersistence();
        TourService tourService = new TourService(modal.getTouDao());
        Dto<List<TourBo>> tourServiceDto = tourService.getGeneralTours();
        closePersistence();

        Dto<List<TourIo>> result = wrapTourList(tourServiceDto.data);
        if (tourServiceDto.status == Dto.Status.error) {
            result.setError("Controller: Ошибка при получении списка общепользовательских туров");
            tourServiceDto.errorMsgList.forEach(result::addErrorMsg);
        }
        return result;
    }

    /*
     * Работа с сервисом, отвечающим за сессии пользователя
     * */

    @Override
    public Dto<List<TourIo>> getToursFromUserSessionWithStatus(StatusAndUserIo info) {

        openPersistence();
        UserService userService = new UserService(modal.getUserDao());
        Dto<List<TourBo>> userServiceDto = userService.getToursFromSessionsWithStatus(info.userId, info.status);
        closePersistence();

        Dto<List<TourIo>> result = wrapTourList(userServiceDto.data);
        if (userServiceDto.status == Dto.Status.error) {
            result.setError("Controller: Ошибка при получении туров из пользовательских сессий с указанным статусом");
            userServiceDto.errorMsgList.forEach(result::addErrorMsg);
        }
        return result;
    }

    @Override
    public Dto<List<SessionIo>> getAllUserSessions(UserIdIo info) {

        openPersistence();
        UserService userService = new UserService(modal.getUserDao());
        Dto<List<SessionBo>> userServiceDto = userService.getSessionList(info.key);
        closePersistence();

        Dto<List<SessionIo>> result = wrapSessionList(userServiceDto.data);
        if (userServiceDto.status == Dto.Status.error) {
            result.setError("Controller: Ошибка при получении туров из всех пользовательскитх сессий");
            userServiceDto.errorMsgList.forEach(result::addErrorMsg);
        }
        return result;
    }

    @Override
    public Dto<SessionIo> createUserSession(UserSessionIo info) {

        openPersistence();
        UserService userService = new UserService(modal.getUserDao());
        Dto<SessionBo> userServiceDto = userService.addUserSession(info.userId, convertUserSessionIo(info));
        closePersistence();

        Dto<SessionIo> result = wrapUserSession(userServiceDto.data);
        if (userServiceDto.status == Dto.Status.error) {
            result.setError("Controller: Ошибка при создании пользовательской сессии");
            userServiceDto.errorMsgList.forEach(result::addErrorMsg);
        }
        return result;
    }

    @Override
    public Dto<SessionIo> updateUserSession(UserSessionIo info) {

        openPersistence();
        UserService userService = new UserService(modal.getUserDao());
        Dto<SessionBo> userServiceDto = userService.updateSession(info.userId, convertUserSessionIo(info));
        closePersistence();

        Dto<SessionIo> result = wrapUserSession(userServiceDto.data);
        if (userServiceDto.status == Dto.Status.error) {
            result.setError("Controller: Ошибка при изменении пользовательской сессии");
            userServiceDto.errorMsgList.forEach(result::addErrorMsg);
        }
        return result;
    }
}
