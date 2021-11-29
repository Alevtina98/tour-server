package ru.krista.tour.controller.domains;

import ru.krista.tour.model.data.dao.TourDao;
import ru.krista.tour.model.data.dao.UserDao;

public interface IModal {
    public boolean openGateway () ;
    public boolean closeGateway();
    public UserDao getUserTourDao();
    public TourDao getTouDao ();
}
