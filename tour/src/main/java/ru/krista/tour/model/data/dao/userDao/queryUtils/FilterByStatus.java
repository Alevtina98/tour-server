package ru.krista.tour.model.data.dao.userDao.queryUtils;

import ru.krista.tour.model.data.persistence.entities.UserTour;
import ru.krista.tour.model.data.persistence.queryUtils.IColumnFilter;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

public class FilterByStatus implements IColumnFilter<UserTour> {
    public String targetValue;
    public Path<Object> rootColumnValue (Root<UserTour> rootEntity) {
        return rootEntity.get("status");
    };
    public Object targetColumnValue () {
        return targetValue;
    }

    public FilterByStatus (String targetValue) {
        this.targetValue = targetValue;
    }
}