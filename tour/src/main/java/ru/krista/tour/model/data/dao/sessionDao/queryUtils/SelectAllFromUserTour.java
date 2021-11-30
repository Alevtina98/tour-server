package ru.krista.tour.model.data.dao.sessionDao.queryUtils;

import ru.krista.tour.model.data.persistence.entities.Session;
import ru.krista.tour.model.data.persistence.queryUtils.ISelectParams;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

public class SelectAllFromUserTour implements ISelectParams<Session, Session> {
    public Class<Session> classFromEntity () {
        return Session.class;
    };
    public Class<Session> classResult () {
        return Session.class;
    };
    public Path<Session> result (Root<Session> fromEntity) {
        return fromEntity;
    };
}
