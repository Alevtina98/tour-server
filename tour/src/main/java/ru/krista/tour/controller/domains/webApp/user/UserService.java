package ru.krista.tour.controller.domains.webApp.user;

import ru.krista.tour.Dto;
import ru.krista.tour.controller.domains.webApp.user.session.SessionBo;
import ru.krista.tour.controller.domains.webApp.user.session.tour.TourBo;
import ru.krista.tour.view.resources.presentations.informationObjects.UserSessionIo;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    public Dto<List<TourBo>> getToursFromSessionsWithStatus (String userId, SessionBo.StatusVariant status) {

        List<TourBo> tourBoList = new ArrayList<TourBo>() ;
        return new Dto<>(tourBoList);
    }
    public Dto<List<SessionBo>> getSessionList(UserBo userBo) {
        return new Dto<>(userBo.sessionList);
    };
    public Dto<List<SessionBo>> getSessionsByTourAnd(UserBo userBo) {
        return new Dto<>(userBo.sessionList);
    };
    public Dto<SessionBo> getSession (UserBo userBo, Number tourId) {
        // SessionBo sessionBo = userBo.sessionList;
        // userBo.sessionList.stream().filter(session -> session.tourBo.id.equals(tourId)).collect(Collectors.toList());
        return null;
    };
    public Dto<Object> addSession (UserBo userBo, SessionBo sessionBo) {
        userBo.sessionList.add(sessionBo);
        return new Dto<>(null);
    };
    public Dto<UserSessionIo> updateSession (UserBo userBo, SessionBo sessionBo) {
        return null;
    };
}
