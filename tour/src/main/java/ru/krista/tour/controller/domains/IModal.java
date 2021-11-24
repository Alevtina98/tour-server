package ru.krista.tour.controller.domains;

import ru.krista.tour.model.data.dataAccessObjects.TourDao;

public interface IModal {
    public void openGateway () ;
    public void closeGateway();
    public void getUserTourDao();
    public TourDao getTouDao ();
}
