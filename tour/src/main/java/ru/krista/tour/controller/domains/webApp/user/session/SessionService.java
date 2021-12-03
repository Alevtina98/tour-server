package ru.krista.tour.controller.domains.webApp.user.session;

import ru.krista.tour.controller.domains.webApp.user.session.tour.TourService;
import ru.krista.tour.model.data.dataObjects.SessionDo;

import java.util.List;
import java.util.stream.Collectors;

public class SessionService {
    public static enum StatusVariant {
        APPOINTED("назначен"),
        DELAYED("отложен"),
        INTERRUPTED("прерван"),
        PASSED("пройден");

        private final String title;

        StatusVariant(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }
    public static SessionDo convertSessionBo(SessionBo sessionBo) {
        SessionDo sessionDo = new SessionDo();
        sessionDo.id = sessionBo.id;
        sessionDo.status = sessionBo.status.toString();
        sessionDo.dateChange = sessionBo.dateChange;
        sessionDo.tour = TourService.convertTourBo(sessionBo.tour);
        return sessionDo;
    }

    public static SessionBo convertSessionDo(SessionDo sessionDo) {
        SessionBo sessionBo = new SessionBo();
        sessionBo.id = sessionDo.id;
        sessionBo.status = SessionService.StatusVariant.valueOf(sessionDo.status);
        sessionBo.dateChange = sessionDo.dateChange;
        sessionBo.tour = TourService.convertTourDo(sessionDo.tour);
        return sessionBo;
    }

    public static List<SessionBo> convertSessionDo(List<SessionDo> sessionDoList) {
        return sessionDoList.stream().map(SessionService::convertSessionDo).collect(Collectors.toList());
    }
}



