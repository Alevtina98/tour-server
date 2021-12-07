package ru.krista.tour.model.data.dao.tourDao.queryUtils;

import ru.krista.tour.model.data.persistence.entities.Tour;
import ru.krista.tour.model.data.persistence.queryUtils.ISelectParams;

import javax.faces.bean.ApplicationScoped;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

@ApplicationScoped
public class SelectAllFromTour implements ISelectParams<Tour, Tour> {
    public Class<Tour> classFromEntity () {
        return Tour.class;
    };
    public Class<Tour> classResult () {
        return Tour.class;
    };
    public Path<Tour> result(Root<Tour> fromEntity) {
        return fromEntity;
    };
}
