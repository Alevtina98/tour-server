package ru.krista.tour.controller.domains;

import ru.krista.tour.model.data.dao.TourDao;

public interface IModal {
    public void openGateway () ;
    public void closeGateway();
    public void getUserTourDao();
    public TourDao getTouDao ();
}
