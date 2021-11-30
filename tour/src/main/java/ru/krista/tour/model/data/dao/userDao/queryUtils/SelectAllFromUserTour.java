package ru.krista.tour.model.data.dao.userDao.queryUtils;

import ru.krista.tour.model.data.persistence.entities.UserTour;
import ru.krista.tour.model.data.persistence.queryUtils.ISelectParams;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

public class SelectAllFromUserTour implements ISelectParams<UserTour, UserTour> {
    public Class<UserTour> classFromEntity () {
        return UserTour.class;
    };
    public Class<UserTour> classResult () {
        return UserTour.class;
    };
    public Path<UserTour> result (Root<UserTour> fromEntity) {
        return fromEntity;
    };
}
