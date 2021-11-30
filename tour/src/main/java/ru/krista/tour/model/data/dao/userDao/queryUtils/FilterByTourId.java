package ru.krista.tour.model.data.dao.userDao.queryUtils;

import ru.krista.tour.model.data.persistence.entities.UserTour;
import ru.krista.tour.model.data.persistence.queryUtils.IColumnFilter;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

public class FilterByTourId implements IColumnFilter<UserTour> {
    public Number targetValue;
    public Path<Object> rootColumnValue (Root<UserTour> rootEntity) {
        return rootEntity.get("tour").get("id");
    }
    public Object targetColumnValue () {
        return targetValue;
    }

    public FilterByTourId (Number targetValue) {
        this.targetValue = targetValue;
    }
}