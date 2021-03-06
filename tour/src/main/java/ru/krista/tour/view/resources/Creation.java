package ru.krista.tour.view.resources;

import ru.krista.tour.Dto;
import ru.krista.tour.controller.IController;
import ru.krista.tour.view.resources.presentations.informationObjects.*;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/creation")
public class Creation {
    @Inject
    IController controller;
    @Inject
    IPresenter presenter;
    /**
     * Вход: -
     * Выход: все туры, хранящиеся в БД
     **/
    @GET
    @Produces("application/json")
    public Response getTourList() {
        Dto<List<TourIo>> dto = controller.getAllTours();
        return presenter.response(dto);
    }
    /**
     * Вход: идентификатор тура
     * Выход: тур, полученный из БД по идентификатору
     **/
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getTour(@PathParam("id") Long id) {
        TourIdIo informationObject = new TourIdIo(id);
        Dto<TourIo> dto = controller.getTour(informationObject);
        return presenter.response(dto);
    }
    /**
     * Вход: Тур для добавления в БД
     * Выход: Тур из БД, полученный после сохранения
     **/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response getInsertedTour(NewTourIo informationObject) {
        Dto<TourIo> dto = controller.createTour(informationObject);
        return presenter.response(dto);
    }
    /**
     * Вход: Тур для изменения в БД
     * Выход: Тур из БД, полученный после изменения
     **/
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response getUpdatedTour(TourIo informationObject) {
        Dto<TourIo> dto = controller.changeTour(informationObject);
        return presenter.response(dto);
    }
    /**
     * Вход: идентификатор удаляемого тура
     * Выход: -
     **/
    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response getDeletedTourStatus(@PathParam("id") Long id) {
        TourIdIo informationObject = new TourIdIo(id);
        Dto<EmptyIo> dto = controller.deleteTour(informationObject);
        return presenter.response(dto);
    }
}