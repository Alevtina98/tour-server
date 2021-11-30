package ru.krista.tour.controller.services;

import ru.krista.tour.Dto;
import ru.krista.tour.controller.domains.webApp.user.session.SessionBo;
import ru.krista.tour.model.data.persistence.entities.Session;
import ru.krista.tour.model.data.persistence.entities.Tour;
import ru.krista.tour.model.data.persistence.entities.UserTourKey;

import java.util.List;

public interface ISessionDao {
    public Dto<Session> createUserSession(String userId, SessionBo newSession);
    public Dto<Session> readUserSession(String userId);
    public Dto<Session> updateUserSession(String userId, SessionBo editSession);
    public Dto<Session> deleteUserSession(UserTourKey sessionId);

    public Dto<List<Session>> readUserSessionListWithStatus(String userId, SessionBo.StatusVariant status);
    public Dto<List<Tour>> readUserTourListWithSessionStatus(String userId, SessionBo.StatusVariant status);
    public Dto<List<Tour>> readUserTourList(String userId);
}
