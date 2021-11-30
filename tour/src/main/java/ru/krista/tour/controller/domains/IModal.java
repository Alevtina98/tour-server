package ru.krista.tour.controller.domains;

import ru.krista.tour.model.data.dao.tourDao.TourDao;
import ru.krista.tour.model.data.dao.sessionDao.SessionDao;

public interface IModal {
    public boolean openGateway () ;
    public boolean closeGateway();
    public SessionDao getUserTourDao();
    public TourDao getTouDao ();
}
