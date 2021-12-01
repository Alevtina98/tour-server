package ru.krista.tour.controller.services;

import ru.krista.tour.Dto;
import ru.krista.tour.controller.domains.webApp.user.UserBo;
import ru.krista.tour.controller.domains.webApp.user.session.SessionBo;
import ru.krista.tour.controller.domains.webApp.user.session.SessionService;

public interface IUserService {
    public Dto<UserBo> getUser(String userId); // содержит все сессии
    public Dto<UserBo> getUserWithNewSession(String userId, SessionBo newSession);
    public Dto<UserBo> getUserWithEditSession(String userId, SessionBo editSession);
    public Dto<UserBo> getUserWithFilterSessionByStatus(String userId, SessionService.StatusVariant status);
}
