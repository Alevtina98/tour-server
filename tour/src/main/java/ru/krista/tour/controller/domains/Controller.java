package ru.krista.tour.controller.domains;

import ru.krista.tour.Dto;
import ru.krista.tour.controller.domains.webApp.user.UserService;
import ru.krista.tour.controller.domains.webApp.user.session.SessionBo;
import ru.krista.tour.controller.domains.webApp.user.session.SessionService;
import ru.krista.tour.model.IModal;
import ru.krista.tour.model.data.Model;
import ru.krista.tour.controller.IController;
import ru.krista.tour.controller.domains.webApp.user.session.tour.TourBo;
import ru.krista.tour.controller.domains.webApp.user.session.tour.TourService;
import ru.krista.tour.controller.domains.webApp.user.session.tour.program.LearningProgramBo;
import ru.krista.tour.controller.domains.webApp.user.session.tour.program.focus.FocusBo;
import ru.krista.tour.controller.domains.webApp.user.session.tour.program.focus.target.TargetBo;
import ru.krista.tour.view.resources.presentations.informationObjects.*;

import javax.enterprise.inject.New;
import javax.faces.bean.ApplicationScoped;
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
@ApplicationScoped
public class Controller implements IController {
    @Inject
    IModal modal;

    private void openPersistence() {
        modal.openGateway();
    }

    private void closePersistence() {
        modal.closeGateway();
    }
    private void errorClosePersistence() {
        modal.errorCloseGateway();
    }


    /*
     * Перенесение данных из информационных объектов в домены
     * */
    private TargetBo convertTargetIo(NewTourIo tourIo) {
        return new TargetBo(tourIo.formName, tourIo.formCaption);
    }

    private FocusBo convertFocusIo(NewTourIo tourIo) {
        return new FocusBo(tourIo.isGeneralUser, convertTargetIo(tourIo));
    }

    private LearningProgramBo convertLearningProgramIo(NewTourIo tourIo) {
        return new LearningProgramBo(tourIo.code, tourIo.codeJS, convertFocusIo(tourIo));
    }

    private TourBo convertTemplateTourIo(NewTourIo tourIo) {
        TourBo tourBo = new TourBo();
        tourBo.name = tourIo.name;
        tourBo.desc = tourIo.desc;
        tourBo.learningProgram = convertLearningProgramIo(tourIo);
        return tourBo;
    }

    private TourBo convertTourIo(TourIo tourIo) {
        TourBo tourBo = convertTemplateTourIo(tourIo);
        tourBo.id = tourIo.id;
        tourBo.dateCreate = tourIo.dateCreate;
        tourBo.dateChange = tourIo.dateChange;
        return tourBo;
    }

    private SessionBo convertTemplateUserSessionIo(NewUserSessionIo newUserSessionIo) {
        SessionBo session = new SessionBo();
        session.status = newUserSessionIo.status;
        return session;
    }

    private SessionBo convertUserSessionIo(UserSessionIo userSessionIo) {
        SessionBo session = convertTemplateUserSessionIo(userSessionIo);
        session.id = userSessionIo.id;
        return session;
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
        tourIo.dateCreate = tourBo.dateCreate;
        tourIo.dateChange = tourBo.dateChange;
        tourIo.isGeneralUser = tourBo.learningProgram.focus.isGeneralUser;
        if (tourBo.learningProgram.focus.target != null) {
            tourIo.formName = tourBo.learningProgram.focus.target.formName;
            tourIo.formCaption = tourBo.learningProgram.focus.target.formCaption;
        }
        return tourIo;
    }

    private SessionIo convertSessionBo(SessionBo session) {
        SessionIo sessionIo = new SessionIo();
        sessionIo.tour = convertTourBo(session.tour);
        sessionIo.id = session.id;
        sessionIo.dateChange = session.dateChange;
        sessionIo.status = session.status.toString();
        return sessionIo;
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

    private Dto<List<SessionIo>> wrapSessionList(List<SessionBo> sessionList) {
        List<SessionIo> sessionIoList = sessionList.stream().map(this::convertSessionBo).collect(Collectors.toList());
        return new Dto<>(sessionIoList);
    }

    private Dto<SessionIo> wrapUserSession(SessionBo session) {
        SessionIo sessionIo = convertSessionBo(session);
        return new Dto<>(sessionIo);
    }

    /*
     * Работа с сервисом, отвечающим за туры
     * */

    @Override
    public Dto<TourIo> createTour(NewTourIo info) {

        openPersistence();
        TourService tourService = new TourService(modal.getTouDao());
        Dto<TourBo> tourServiceDto = tourService.createTour(convertTemplateTourIo(info));
        if (tourServiceDto.status == Dto.Status.error) {
            errorClosePersistence();
            Dto<TourIo> result = new Dto<>(null);
            result.setError("Controller: Ошибка при создании тура");
            result.addErrorMsg(tourServiceDto.errorMsgList);
            return result;
        }

        openPersistence();
        tourServiceDto = tourService.getTour(tourServiceDto.data.id);
        closePersistence();

        return  wrapTour(tourServiceDto.data);
    }

    @Override
    public Dto<TourIo> changeTour(TourIo info) {

        openPersistence();
        TourService tourService = new TourService(modal.getTouDao());
        Dto<TourBo> tourServiceDto = tourService.changeTour(convertTourIo(info));
        if (tourServiceDto.status == Dto.Status.error) {
            errorClosePersistence();
            Dto<TourIo> result = new Dto<>(null);
            result.setError("Controller: Ошибка при изменении тура");
            result.addErrorMsg(tourServiceDto.errorMsgList);
        }
        closePersistence();

        openPersistence();
        tourServiceDto = tourService.getTour(tourServiceDto.data.id);
        closePersistence();

        return  wrapTour(tourServiceDto.data);
    }

    @Override
    public Dto<EmptyIo> deleteTour(TourIdIo info) {

        openPersistence();
        TourService tourService = new TourService(modal.getTouDao());
        Dto<TourBo> tourServiceDto = tourService.deleteTour(info.id);
        closePersistence();

        Dto<EmptyIo> result = new Dto<>(null);
        if (tourServiceDto.status == Dto.Status.error) {
            result.setError("Controller: Ошибка при удалении тура");
            result.addErrorMsg(tourServiceDto.errorMsgList);
        }
        return result;
    }

    @Override
    public Dto<TourIo> getTour(TourIdIo info) {

        openPersistence();
        TourService tourService = new TourService(modal.getTouDao());
        Dto<TourBo> tourServiceDto = tourService.getTour(info.id);
        closePersistence();

        Dto<TourIo> result = wrapTour(tourServiceDto.data);
        if (tourServiceDto.status == Dto.Status.error) {
            result.setError("Controller: Ошибка при получении тура");
            result.addErrorMsg(tourServiceDto.errorMsgList);
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
            result.addErrorMsg(tourServiceDto.errorMsgList);
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
            result.addErrorMsg(tourServiceDto.errorMsgList);
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
            result.addErrorMsg(userServiceDto.errorMsgList);
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
            result.addErrorMsg(userServiceDto.errorMsgList);
        }
        return result;
    }

    @Override
    public Dto<SessionIo> createUserSession(NewUserSessionIo info) {

        SessionBo session = convertTemplateUserSessionIo(info);
        Dto<SessionIo> result = new Dto<>(null);

        openPersistence();
        UserService userService = new UserService(modal.getUserDao());
        TourService tourService = new TourService(modal.getTouDao());
        session.tour = tourService.getTour(info.tourId).data;
        if (session.tour == null) {
            closePersistence();
            result.setError("Controller: Не найден тур для создаваемой сессии");
            return result;
        }
        Dto<SessionBo> userServiceDto = userService.addSession(info.userId, session);
        if (userServiceDto.status == Dto.Status.error) {
            errorClosePersistence();
            result.setError("Controller: Ошибка при создании пользовательской сессии");
            result.addErrorMsg(userServiceDto.errorMsgList);
            return result;
        }
        closePersistence();

        openPersistence();
        userServiceDto = userService.getSession(info.userId, userServiceDto.data.tour.id);
        closePersistence();

        result.setData(convertSessionBo(userServiceDto.data));
        return result;
    }

    @Override
    public Dto<SessionIo> updateUserSession(UserSessionIo info) {


        SessionBo session = convertUserSessionIo(info);
        Dto<SessionIo> result = new Dto<>(null);

        openPersistence();
        UserService userService = new UserService(modal.getUserDao());
        TourService tourService = new TourService(modal.getTouDao());
        session.tour = tourService.getTour(info.tourId).data;
        if (session.tour == null) {
            closePersistence();
            result.setError("Controller: Не найден тур для изеняемой сессии");
            return result;
        }
        Dto<SessionBo> userServiceDto = userService.updateSession(info.userId, session);

        if (userServiceDto.status == Dto.Status.error) {
            errorClosePersistence();
            result.setError("Controller: Ошибка при изменении пользовательской сессии");
            result.addErrorMsg(userServiceDto.errorMsgList);
            return result;
        }
        closePersistence();

        openPersistence();
        userServiceDto = userService.getSession(info.userId, userServiceDto.data.tour.id);
        closePersistence();

        result.setData(convertSessionBo(userServiceDto.data));
        return result;
    }
}
