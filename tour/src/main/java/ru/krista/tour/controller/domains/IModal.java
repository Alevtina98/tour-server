package ru.krista.tour.controller.domains;

import ru.krista.tour.model.data.dao.tourDao.TourDao;
import ru.krista.tour.model.data.dao.userDao.UserDao;

public interface IModal {
    public boolean openGateway () ;
    public boolean closeGateway();
    public UserDao getUserTourDao();
    public TourDao getTouDao ();
}
