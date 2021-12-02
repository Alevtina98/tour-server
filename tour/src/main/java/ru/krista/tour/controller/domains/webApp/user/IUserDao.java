package ru.krista.tour.controller.domains.webApp.user;

import ru.krista.tour.Dto;
import ru.krista.tour.controller.domains.webApp.user.session.SessionBo;
import ru.krista.tour.controller.domains.webApp.user.session.SessionService;
import ru.krista.tour.controller.domains.webApp.user.session.tour.TourBo;
import ru.krista.tour.model.data.persistence.entities.UserTourKey;

import java.util.List;

public interface IUserDao {
    public Dto<SessionBo> createUserSession(String userId, SessionBo newSession);
    public Dto<SessionBo> readUserSession(String userId);
    public Dto<SessionBo> updateUserSession(String userId, SessionBo editSession);
    public Dto<SessionBo> deleteUserSession(UserTourKey sessionId);

    public Dto<List<SessionBo>> readUserSessionListWithStatus(String userId, SessionService.StatusVariant status);
    public Dto<List<TourBo>> readUserTourListWithSessionStatus(String userId, SessionService.StatusVariant status);
    public Dto<List<TourBo>> readUserTourList(String userId);
}
