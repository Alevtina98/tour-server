package ru.krista.tour.controller.domains.webApp.user;

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
        Dto<List<TourBo>> result =  new Dto<>(null);
        if (daoDto.status == Dto.Status.error) {
            result.setError("UserService: Не удалось получить список общепользовательских туров");
            result.addErrorMsg(daoDto.errorMsgList);
        }
        result.setData(TourService.convertTourDo(daoDto.data));
        return result;
    }

    public Dto<List<SessionBo>> getSessionList(String userId) {
        Dto<List<SessionDo>> daoDto = dao.readAllUserSessions(userId);
        Dto<List<SessionBo>> result =  new Dto<>(null);
        if (daoDto.status == Dto.Status.error) {
            result.setError("UserService: Не удалось получить список общепользовательских туров");
            result.addErrorMsg(daoDto.errorMsgList);
        }
        result.setData(SessionService.convertSessionDo(daoDto.data));
        return result;
    }

    public Dto<List<SessionBo>> getSessionsByTourAnd(UserBo userBo) {
        return new Dto<>(userBo.sessionList);
    }

    public Dto<SessionBo> getSession(UserBo userBo, Number tourId) {
        // SessionBo sessionBo = userBo.sessionList;
        // userBo.sessionList.stream().filter(session -> session.tourBo.id.equals(tourId)).collect(Collectors.toList());
        return null;
    }

    public Dto<SessionBo> addSession(String userId, SessionBo sessionBo) {
         /* Long tourId = lesson.tour.id;
        List<LessonIo> sameEntity = findUserTour(lesson.getUserId(), tourId);
        if (!sameEntity.isEmpty()) {
            return response(500, "Данная связь уже существует");
        }
        TourIo tour = findById(TourIo.class, tourId);
        if (tour==null) {
            return response(500, "Назначаемого тура не существует");
        }
        LessonIo insertedUserTour = save(lesson);
        if (insertedUserTour != null){
            return response(200, insertedUserTour);
        }else {
            return response(500);
        }*/
        SessionDo sessionDo =  SessionService.convertSessionBo(sessionBo);
        Dto<SessionDo> daoDto = dao.createSession(sessionDo, userId);
        Dto<SessionBo> result =  new Dto<>(null);
        if (daoDto.status == Dto.Status.error) {
            result.setError("UserService: Не удалось получить список общепользовательских туров");
            result.addErrorMsg(daoDto.errorMsgList);
        }
        result.setData(SessionService.convertSessionDo(daoDto.data));
        return result;
    }

    public Dto<SessionBo> updateSession(String userId, SessionBo sessionBo) {
        SessionDo sessionDo =  SessionService.convertSessionBo(sessionBo);
        Dto<SessionDo> daoDto = dao.updateSession(sessionDo, userId);
        Dto<SessionBo> result =  new Dto<>(null);
        if (daoDto.status == Dto.Status.error) {
            result.setError("UserService: Не удалось получить список общепользовательских туров");
            result.addErrorMsg(daoDto.errorMsgList);
        }
        result.setData(SessionService.convertSessionDo(daoDto.data));
        return result;
    }


}
