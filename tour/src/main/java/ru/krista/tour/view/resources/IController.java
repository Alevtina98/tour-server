package ru.krista.tour.view.resources;

import ru.krista.tour.Dto;
import ru.krista.tour.view.resources.presentations.informationObjects.*;

import java.util.List;

public interface IController {
    public Dto<UserSessionIo> createUserSession(UserSessionIo info);
    public Dto<UserSessionIo> updateUserSession(UserSessionIo info);
    public Dto<List<UserSessionIo>> getUserSessions(UserIdIo info);
    public Dto<List<TourIo>> getToursFromUserSessionWithStatus(StatusAndUserIo info);

    public Dto<List<TourIo>> getToursWithGeneralFocus();
    public Dto<List<TourIo>> getTours();
    public Dto<TourIo> getTour (TourIdIo info);
    public Dto<TourIo> createTour(TourIo info);
    public Dto<TourIo> changeTour(TourIo info);
    public Dto<EmptyIo> deleteTour (TourIdIo info);
}
