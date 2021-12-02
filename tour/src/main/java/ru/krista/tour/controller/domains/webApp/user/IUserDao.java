package ru.krista.tour.controller.domains.webApp.user;

import ru.krista.tour.Dto;
import ru.krista.tour.controller.domains.webApp.user.session.SessionService;
import ru.krista.tour.model.data.SessionDo;
import ru.krista.tour.model.data.TourDo;

import java.util.List;

public interface IUserDao {
    public Dto<SessionDo> createSession(SessionDo sessionDo);

    public Dto<SessionDo> readSession(Long id);

    public Dto<SessionDo> updateSession(SessionDo sessionDo);

    public Dto<SessionDo> deleteSession(Long id);

    public Dto<SessionDo> readUserSessionWithTour(String userId, Long tourId);

    public Dto<List<SessionDo>> readUserSessionListWithTour(String userId, Long tourId);

    public Dto<List<SessionDo>> readUserSessionListWithStatus(String userId, String status);

    public Dto<List<TourDo>> readUserTourListWithSessionStatus(String userId, String status);

    public Dto<List<TourDo>> readToursFromAllUserSessions(String userId);
}
