package ru.krista.tour.model.data.dao.sessionDao;

import ru.krista.tour.Dto;
import ru.krista.tour.controller.domains.webApp.user.session.SessionBo;
import ru.krista.tour.controller.domains.webApp.user.session.SessionService;
import ru.krista.tour.controller.domains.webApp.user.IUserDao;
import ru.krista.tour.controller.domains.webApp.user.session.tour.TourBo;
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

    public Dto<SessionBo> createUserSession(String userId, SessionBo newSession){
        return null;
    };
    public Dto<SessionBo> readUserSession(String userId) {
        return null;
    };
    public Dto<SessionBo> updateUserSession(String userId, SessionBo editSession){
        return null;
    };
    public Dto<SessionBo> deleteUserSession(UserTourKey sessionId){
        return null;
    };

    public Dto<List<SessionBo>> readUserSessionListWithStatus(String userId, SessionService.StatusVariant status){
        return null;
    };
    public Dto<List<TourBo>> readUserTourListWithSessionStatus(String userId, SessionService.StatusVariant status){
        return null;
    };
    public Dto<List<TourBo>> readUserTourList(String userId){
        return null;
    };
}
