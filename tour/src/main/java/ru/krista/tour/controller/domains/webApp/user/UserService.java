package ru.krista.tour.controller.domains.webApp.user;

import ru.krista.tour.Dto;
import ru.krista.tour.controller.domains.webApp.user.session.SessionBo;
import ru.krista.tour.controller.domains.webApp.user.session.tour.TourBo;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    IUserDao dao;

    public UserService(IUserDao dao) {
        this.dao = dao;
    }

    public Dto<List<TourBo>> getToursFromSessionsWithStatus (String userId, String status) {
        //check status as SessionBo.StatusVariant
        List<TourBo> tourBoList = new ArrayList<TourBo>() ;
        return new Dto<>(tourBoList);
    }
    public Dto<List<SessionBo>> getSessionList(String userId) {
        UserBo user = new UserBo(new ArrayList<>(), userId);
        return new Dto<>(user.sessionList);
    };
    public Dto<List<SessionBo>> getSessionsByTourAnd(UserBo userBo) {
        return new Dto<>(userBo.sessionList);
    };
    public Dto<SessionBo> getSession (UserBo userBo, Number tourId) {
        // SessionBo sessionBo = userBo.sessionList;
        // userBo.sessionList.stream().filter(session -> session.tourBo.id.equals(tourId)).collect(Collectors.toList());
        return null;
    };
    public Dto<SessionBo> addUserSession(String userId, SessionBo sessionBo) {
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
        UserBo user = new UserBo(new ArrayList<>(), userId);
        // data from dao
        SessionBo session = new SessionBo();
        user.sessionList.add(sessionBo);
        return new Dto<>(session);
    };
    public Dto<SessionBo> updateSession (String userId, SessionBo sessionBo) {
        return null;
    };
}
