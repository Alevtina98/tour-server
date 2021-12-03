package ru.krista.tour.controller.domains.webApp.user;

import ru.krista.tour.Dto;
import ru.krista.tour.model.data.dataObjects.SessionDo;
import ru.krista.tour.model.data.dataObjects.TourDo;

import java.util.List;

public interface IUserDao {
    public Dto<SessionDo> createSession(SessionDo userSessionDo,String userId);

    public Dto<SessionDo> readSession(Long id);

    public Dto<SessionDo> updateSession(SessionDo sessionDo,String userId);

    public Dto<SessionDo> deleteSession(Long id);

    public Dto<List<SessionDo>> readAllUserSessions(String userId);

    public Dto<SessionDo> readUserSessionWithTour(String userId, Long tourId);

    public Dto<List<SessionDo>> readUserSessionListWithTour(String userId, Long tourId);

    public Dto<List<SessionDo>> readUserSessionListWithStatus(String userId, String status);

    public Dto<List<TourDo>> readTourListFromUserSessionWithStatus(String userId, String status);

    public Dto<List<TourDo>> readTourListFromAllUserSessions(String userId);
}
