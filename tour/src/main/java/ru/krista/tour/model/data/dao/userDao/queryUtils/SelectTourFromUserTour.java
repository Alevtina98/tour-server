package ru.krista.tour.model.data.dao.userDao.queryUtils;

import ru.krista.tour.model.data.persistence.entities.Tour;
import ru.krista.tour.model.data.persistence.entities.UserTour;
import ru.krista.tour.model.data.persistence.queryUtils.ISelectParams;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

public class SelectTourFromUserTour implements ISelectParams<UserTour, Tour> {
    public Class<UserTour> classFromEntity () {
        return UserTour.class;
    };
    public Class<Tour> classResult () {
        return Tour.class;
    };
    public Path<Tour> result (Root<UserTour> fromEntity) {
        return fromEntity.get("tour");
    };
}
