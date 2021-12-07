package ru.krista.tour.model;

import ru.krista.tour.model.data.dao.tourDao.TourDao;
import ru.krista.tour.model.data.dao.userDao.UserDao;

public interface IModal {
    public void openGateway () ;
    public void closeGateway();
    public UserDao getUserDao();
    public TourDao getTouDao ();
}
