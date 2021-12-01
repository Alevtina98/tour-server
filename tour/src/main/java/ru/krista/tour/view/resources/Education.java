package ru.krista.tour.view.resources;


import ru.krista.tour.Dto;
import ru.krista.tour.view.resources.presentations.Presenter;
import ru.krista.tour.view.resources.presentations.informationObjects.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tour/player")
public class Education {
    @Inject
    IController controller;
    @Inject
    Presenter presenter;

    /**
     * Вход: Связь Пользователь-Тур для добавления в БД
     * Выход: Связь Пользователь-Тур из БД, полученная после добавления
     **/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response getNewLesson(UserSessionIo informationObject) {
        Dto<SessionIo> dto = controller.createUserSession(informationObject);
        return presenter.response(dto);
    }

    /**
     * Вход: Связь Пользователь-Тур для изменения в БД
     * Выход: Связь Пользователь-Тур из БД, полученная после изменения
     **/

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response getChangedLesson(UserSessionIo informationObject) {
        Dto<SessionIo> dto = controller.updateUserSession(informationObject);
        return presenter.response(dto);
    }
    /**
     * Вход: -
     * Выход: туры, которые являются общепользовательскими
     **/
    @GET
    @Path("/generalUser")
    @Produces("application/json")
    public Response getGeneralTourList() {
        Dto<List<TourIo>> dto = controller.getToursWithGeneralFocus();
        return presenter.response(dto);
    }
    /**
     * Вход: идентификатор Пользователя
     * Выход: все туры Пользователя, хранящиеся в БД
     **/
    @GET
    @Path("/{userId}")
    @Produces("application/json")
    public Response getLessonList(@PathParam("userId") String userId) {
        UserIdIo informationObject = new UserIdIo(userId);
        Dto<List<SessionIo>> dto = controller.getAllUserSessions(informationObject);
        return presenter.response(dto);
    }
/**
     * Вход: идентификатор Пользователя и тип связи
     * Выход: туры Пользователя с заданным типом связи, хранящиеся в БД
     **/
    @GET
    @Path("/{userId}/{status}")
    @Produces("application/json")
    public Response getTourList(@PathParam("userId") String userId, @PathParam("status") String status) {
        StatusAndUserIo informationObject = new StatusAndUserIo(userId, status);
        Dto<List<TourIo>> dto = controller.getToursFromUserSessionWithStatus(informationObject);
        return presenter.response(dto);
    }
}
