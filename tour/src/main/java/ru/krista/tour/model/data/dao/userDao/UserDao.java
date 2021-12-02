package ru.krista.tour.model.data.dao.userDao;

import ru.krista.tour.Dto;
import ru.krista.tour.controller.domains.webApp.user.IUserDao;
import ru.krista.tour.model.data.SessionDo;
import ru.krista.tour.model.data.TourDo;
import ru.krista.tour.model.data.dao.IProvider;
import ru.krista.tour.model.data.dao.tourDao.TourDao;
import ru.krista.tour.model.data.dao.userDao.queryUtils.*;
import ru.krista.tour.model.data.persistence.entities.Session;
import ru.krista.tour.model.data.persistence.entities.Tour;
import ru.krista.tour.model.data.persistence.queryUtils.IColumnFilter;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDao implements IUserDao {
    IProvider provider;

    public UserDao(IProvider provider) {
        this.provider = provider;
    }

    public static Session convertSessionDo(SessionDo sessionDo) {
        Session sessionEntity = new Session();
        sessionEntity.setId(sessionDo.id);
        sessionEntity.setStatus(sessionDo.status);
        sessionEntity.setUserId(sessionDo.userId);
        sessionEntity.setTour(TourDao.convertTourDo(sessionDo.tour));
        return sessionEntity;
    }

    public static SessionDo convertSessionEntity(Session sessionEntity) {
        SessionDo sessionDo = new SessionDo();
        sessionDo.id = sessionEntity.getId();
        sessionDo.status = sessionEntity.getStatus();
        sessionDo.userId = sessionEntity.getUserId();
        sessionDo.tour = TourDao.convertTourEntity(sessionEntity.getTour());
        return sessionDo;
    }
    public static List<SessionDo> convertSessionEntity(List<Session> sessionEntityList) {
        return sessionEntityList.stream().map(UserDao::convertSessionEntity).collect(Collectors.toList());
    }
    private List<IColumnFilter<Session>> createFilterByUserAndStatus (String userId, String status) {
        List<IColumnFilter<Session>> filterList = new ArrayList<>();
        filterList.add(new FilterByUserId(userId));
        filterList.add(new FilterByStatus(status));
        return filterList;
    }
    private List<IColumnFilter<Session>> createFilterByUserAndTour (String userId, Long tourId) {
        List<IColumnFilter<Session>> filterList = new ArrayList<>();
        filterList.add(new FilterByUserId(userId));
        filterList.add(new FilterByTourId(tourId));
        return filterList;
    }

    @Override
    public Dto<SessionDo> createSession(SessionDo sessionDo) {
        Session sessionEntity = convertSessionDo(sessionDo);
        Dto<Session> providerResult = provider.create(sessionEntity);
        Dto<SessionDo> result = new Dto<>(null);
        if (providerResult.status == Dto.Status.ok) {
            result.setData(convertSessionEntity(providerResult.data));
        } else {
            result.setError("UserDao: Не удалось сохранить данные о сессии");
            result.addErrorMsg(providerResult.errorMsgList);
        }
        return result;
    }

    @Override
    public Dto<SessionDo> readSession(Long id) {
        Dto<Session> providerResult = provider.readById(Session.class, id);
        Dto<SessionDo> result = new Dto<>(null);
        if (providerResult.status == Dto.Status.ok) {
            result.setData(convertSessionEntity(providerResult.data));
        } else {
            result.setError("UserDao: Не удалось прочитать данные о сессии");
            result.addErrorMsg(providerResult.errorMsgList);
        }
        return result;
    }

    @Override
    public Dto<SessionDo> updateSession(SessionDo sessionDo) {
        Session sessionEntity = convertSessionDo(sessionDo);
        Dto<Session> providerResult = provider.update(sessionEntity);
        Dto<SessionDo> result = new Dto<>(null);
        if (providerResult.status == Dto.Status.ok) {
            result.setData(convertSessionEntity(providerResult.data));
        } else {
            result.setError("UserDao: Не удалось обновить данные о сессии");
            result.addErrorMsg(providerResult.errorMsgList);
        }
        return result;
    }

    @Override
    public Dto<SessionDo> deleteSession(Long id) {
        Dto<Session> providerResult = provider.delete(Session.class, id);
        Dto<SessionDo> result = new Dto<>(null);
        if (providerResult.status == Dto.Status.ok) {
            result.setData(convertSessionEntity(providerResult.data));
        } else {
            result.setError("UserDao: Не удалось удалить данные о сессии");
            result.addErrorMsg(providerResult.errorMsgList);
        }
        return result;
    }

    @Override
    public Dto<SessionDo> readUserSessionWithTour(String userId, Long tourId) {
        Dto<List<Session>> providerResult = provider.readWithValueFilter(new SelectAllFromSession(), createFilterByUserAndTour(userId, tourId));
        Dto<SessionDo> result = new Dto<>(null);
        if (providerResult.status == Dto.Status.ok) {
            result.setData(convertSessionEntity(providerResult.data.get(0)));
        } else {
            result.setError("UserDao: Не удалось прочитать данные о сессии");
            result.addErrorMsg(providerResult.errorMsgList);
        }
        return result;
    }
    @Override
    public Dto<List<SessionDo>> readUserSessionListWithTour(String userId, Long tourId) {
        Dto<List<Session>> providerResult = provider.readWithValueFilter(new SelectAllFromSession(), createFilterByUserAndTour(userId, tourId));
        Dto<List<SessionDo>> result = new Dto<>(null);
        if (providerResult.status == Dto.Status.ok) {
            result.setData(convertSessionEntity(providerResult.data));
        } else {
            result.setError("UserDao: Не удалось прочитать данные о сессии");
            result.addErrorMsg(providerResult.errorMsgList);
        }
        return result;
    }

    @Override
    public Dto<List<TourDo>> readToursFromAllUserSessions(String userId) {
        Dto<List<Tour>> providerResult = provider.readWithValueFilter(new SelectTourFromSession(), new FilterByUserId(userId));
        Dto<List<TourDo>> result = new Dto<>(null);
        if (providerResult.status == Dto.Status.ok) {
            result.setData(TourDao.convertTourEntity(providerResult.data));
        } else {
            result.setError("UserDao: Не удалось прочитать данные о сессии");
            result.addErrorMsg(providerResult.errorMsgList);
        }
        return result;
    }


    @Override
    public Dto<List<SessionDo>> readUserSessionListWithStatus(String userId, String status) {
        Dto<List<Session>> providerResult = provider.readWithValueFilter(new SelectAllFromSession(), createFilterByUserAndStatus(userId, status));
        Dto<List<SessionDo>> result = new Dto<>(null);
        if (providerResult.status == Dto.Status.ok) {
            result.setData(convertSessionEntity(providerResult.data));
        } else {
            result.setError("UserDao: Не удалось прочитать данные о сессиях с указанным статусом");
            result.addErrorMsg(providerResult.errorMsgList);
        }
        return result;
    }

    @Override
    public Dto<List<TourDo>> readUserTourListWithSessionStatus(String userId, String status) {
        Dto<List<Tour>> providerResult = provider.readWithValueFilter(new SelectTourFromSession(), createFilterByUserAndStatus(userId, status));
        Dto<List<TourDo>> result = new Dto<>(null);
        if (providerResult.status == Dto.Status.ok) {
            result.setData(TourDao.convertTourEntity(providerResult.data));
        } else {
            result.setError("UserDao: Не удалось прочитать данные о турах из сессий с указанным статусом");
            result.addErrorMsg(providerResult.errorMsgList);
        }
        return result;
    }

}
