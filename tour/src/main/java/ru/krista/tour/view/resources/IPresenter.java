package ru.krista.tour.view.resources;

import ru.krista.tour.Dto;

import javax.ws.rs.core.Response;

public interface IPresenter {
    public <TInformationObject> Response response(Dto<TInformationObject> dto);
}