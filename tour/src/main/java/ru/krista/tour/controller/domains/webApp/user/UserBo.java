package ru.krista.tour.controller.domains.webApp.user;

import ru.krista.tour.controller.domains.webApp.user.session.SessionBo;

import java.util.List;

/*
** ОБУЧАЮЩИЙСЯ (пользователь приложения)
 */
public class UserBo {
    String id;
    List<SessionBo> sessionList;
    // агрегирует объекты сессий (уроки)
    public UserBo (List<SessionBo> sessionList, String id) {
        this.sessionList = sessionList;
    }
}
