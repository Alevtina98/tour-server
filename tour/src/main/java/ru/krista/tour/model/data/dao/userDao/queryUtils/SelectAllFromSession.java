package ru.krista.tour.model.data.dao.userDao.queryUtils;

import ru.krista.tour.model.data.persistence.entities.Session;
import ru.krista.tour.model.data.persistence.queryUtils.ISelectParams;

import javax.faces.bean.ApplicationScoped;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
@ApplicationScoped
public class SelectAllFromSession implements ISelectParams<Session, Session> {
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
