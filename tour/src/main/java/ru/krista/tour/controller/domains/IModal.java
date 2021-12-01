package ru.krista.tour.controller.domains;

import ru.krista.tour.model.data.dao.tourDao.TourDao;
import ru.krista.tour.model.data.dao.sessionDao.UserDao;

public interface IModal {
    public void openGateway () ;
    public void closeGateway();
    public UserDao getUserDao();
    public TourDao getTouDao ();
}
