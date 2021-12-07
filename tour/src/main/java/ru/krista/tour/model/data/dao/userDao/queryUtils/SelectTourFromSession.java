package ru.krista.tour.model.data.dao.userDao.queryUtils;

import ru.krista.tour.model.data.persistence.entities.Session;
import ru.krista.tour.model.data.persistence.entities.Tour;
import ru.krista.tour.model.data.persistence.queryUtils.ISelectParams;

import javax.faces.bean.ApplicationScoped;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
@ApplicationScoped
public class SelectTourFromSession implements ISelectParams<Session, Tour> {
    public Class<Session> classFromEntity () {
        return Session.class;
    };
    public Class<Tour> classResult () {
        return Tour.class;
    };
    public Path<Tour> result (Root<Session> fromEntity) {
        return fromEntity.get("tour");
    };
}
