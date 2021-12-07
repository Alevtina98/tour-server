package ru.krista.tour.controller;

import ru.krista.tour.Dto;
import ru.krista.tour.view.resources.presentations.informationObjects.*;

import java.util.List;

public interface IController {
    public Dto<SessionIo> createUserSession(UserSessionIo info);
    public Dto<SessionIo> updateUserSession(UserSessionIo info);
    public Dto<List<SessionIo>> getAllUserSessions(UserIdIo info);
    public Dto<List<TourIo>> getToursFromUserSessionWithStatus(StatusAndUserIo info);

    public Dto<List<TourIo>> getToursWithGeneralFocus();
    public Dto<List<TourIo>> getAllTours();
    public Dto<TourIo> getTour (TourIdIo info);
    public Dto<TourIo> createTour(TourIo info);
    public Dto<TourIo> changeTour(TourIo info);
    public Dto<EmptyIo> deleteTour (TourIdIo info);
}
