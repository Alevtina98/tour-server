package ru.krista.tour.model.data.dao.sessionDao;

import ru.krista.tour.Dto;
import ru.krista.tour.controller.domains.webApp.user.session.SessionBo;
import ru.krista.tour.controller.domains.webApp.user.session.SessionService;
import ru.krista.tour.controller.services.IUserDao;
import ru.krista.tour.model.data.dao.IProvider;
import ru.krista.tour.model.data.persistence.entities.Session;
import ru.krista.tour.model.data.persistence.entities.Tour;
import ru.krista.tour.model.data.persistence.entities.UserTourKey;

import java.util.List;

public class UserDao implements IUserDao {
    IProvider provider;

    public UserDao(IProvider provider) {
        this.provider = provider;
    }

    public Dto<Session> createUserSession(String userId, SessionBo newSession){
        return null;
    };
    public Dto<Session> readUserSession(String userId) {
        return null;
    };
    public Dto<Session> updateUserSession(String userId, SessionBo editSession){
        return null;
    };
    public Dto<Session> deleteUserSession(UserTourKey sessionId){
        return null;
    };

    public Dto<List<Session>> readUserSessionListWithStatus(String userId, SessionService.StatusVariant status){
        return null;
    };
    public Dto<List<Tour>> readUserTourListWithSessionStatus(String userId, SessionService.StatusVariant status){
        return null;
    };
    public Dto<List<Tour>> readUserTourList(String userId){
        return null;
    };
}
