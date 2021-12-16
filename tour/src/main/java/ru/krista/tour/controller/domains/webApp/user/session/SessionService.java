package ru.krista.tour.controller.domains.webApp.user.session;

import ru.krista.tour.controller.domains.webApp.user.session.tour.TourService;
import ru.krista.tour.model.data.dataObjects.SessionDo;

import java.lang.reflect.Array;
import java.util.Arrays;
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

    public static boolean isInEnum(String value) {
        StatusVariant[] list = StatusVariant.class.getEnumConstants();
        return Arrays.stream(list).anyMatch(e -> {
            String name = e.title;
            return name.equals(value);
        });
    }

    public static SessionDo convertSessionBo(SessionBo sessionBo) {
        SessionDo sessionDo = new SessionDo();
        sessionDo.id = sessionBo.id;
        sessionDo.dateChange = sessionBo.dateChange;
        sessionDo.tour = TourService.convertTourBo(sessionBo.tour);
        sessionDo.status = sessionBo.status;
        if (isInEnum(sessionDo.status)) {
            return sessionDo;
        }
        return null;
    }

    public static SessionBo convertSessionDo(SessionDo sessionDo) {
        SessionBo sessionBo = new SessionBo();
        sessionBo.id = sessionDo.id;
        sessionBo.dateChange = sessionDo.dateChange;
        sessionBo.status = sessionDo.status;
        sessionBo.tour = TourService.convertTourDo(sessionDo.tour);
        if (isInEnum(sessionBo.status)) {
            return sessionBo;
        }
        return null;

    }

    public static List<SessionBo> convertSessionDo(List<SessionDo> sessionDoList) {
        return sessionDoList.stream().map(SessionService::convertSessionDo).collect(Collectors.toList());
    }
}



