package ru.krista.tour.controller.domains.webApp.user;

import org.jboss.resteasy.spi.UnhandledException;
import ru.krista.tour.Dto;
import ru.krista.tour.controller.domains.webApp.user.session.SessionBo;
import ru.krista.tour.controller.domains.webApp.user.session.SessionService;
import ru.krista.tour.controller.domains.webApp.user.session.tour.TourBo;
import ru.krista.tour.controller.domains.webApp.user.session.tour.TourService;
import ru.krista.tour.model.data.dataObjects.SessionDo;
import ru.krista.tour.model.data.dataObjects.TourDo;

import java.util.List;

public class UserService {
    IUserDao dao;

    public UserService(IUserDao dao) {
        this.dao = dao;
    }

    public Dto<List<TourBo>> getToursFromSessionsWithStatus(String userId, String status) {
        Dto<List<TourDo>> daoDto = dao.readTourListFromUserSessionWithStatus(userId, status);
        Dto<List<TourBo>> result = new Dto<>(null);
        if (daoDto.status == Dto.Status.error) {
            result.setError("UserService: Не удалось получить список общепользовательских туров");
            result.addErrorMsg(daoDto.errorMsgList);
        }
        result.setData(TourService.convertTourDo(daoDto.data));
        return result;
    }

    public Dto<List<SessionBo>> getSessionList(String userId) {
        Dto<List<SessionDo>> daoDto = dao.readAllUserSessions(userId);
        Dto<List<SessionBo>> result = new Dto<>(null);
        if (daoDto.status == Dto.Status.error) {
            result.setError("UserService: Не удалось получить список сессий");
            result.addErrorMsg(daoDto.errorMsgList);
        }
        result.setData(SessionService.convertSessionDo(daoDto.data));
        return result;
    }

    public Dto<SessionBo> getSession(String userId, Long tourId) {
        Dto<SessionDo> daoDto = dao.readUserSessionWithTour(userId, tourId);
        Dto<SessionBo> result = new Dto<>(null);
        if (daoDto.status == Dto.Status.error) {
            result.setError("UserService: Не удалось получить суссию");
            result.addErrorMsg(daoDto.errorMsgList);
        }
        result.setData(SessionService.convertSessionDo(daoDto.data));
        return result;
    }

    public Dto<SessionBo> addSession(String userId, SessionBo sessionBo) {
        Dto<SessionBo> result = new Dto<>(null);
        SessionDo sessionDo = SessionService.convertSessionBo(sessionBo);
        if (sessionDo == null) {
            result.setError("UserService: Получена некорректная информация о сессии");
            return result;
        }
        try {
            dao.readUserSessionWithTour(userId, sessionDo.tour.id);
            result.setError("UserService: Данная сессия уже существует");
            return result;
        } catch (IndexOutOfBoundsException e) {
            Dto<SessionDo> daoDto = dao.createSession(sessionDo, userId);
            if (daoDto.status == Dto.Status.error) {
                result.setError("UserService: Не удалось создать сессию");
                result.addErrorMsg(daoDto.errorMsgList);
                return result;
            }
            SessionBo resultSessionBo = SessionService.convertSessionDo(daoDto.data);
            result.setData(resultSessionBo);
            return result;
        }
    }

    public Dto<SessionBo> updateSession(String userId, SessionBo sessionBo) {
        Dto<SessionBo> result = new Dto<>(null);
        SessionDo sessionDo = SessionService.convertSessionBo(sessionBo);
        if (sessionDo == null) {
            result.setError("UserService: Получена некорректная информация о сессии");
            return result;
        }
        Dto<SessionDo> daoDto = dao.updateSession(sessionDo, userId);
        if (daoDto.status == Dto.Status.error) {
            result.setError("UserService: Не удалось получить список общепользовательских туров");
            result.addErrorMsg(daoDto.errorMsgList);
        }
        result.setData(SessionService.convertSessionDo(daoDto.data));
        return result;
    }


}
