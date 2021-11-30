package ru.krista.tour.model.data.dao.userDao.queryUtils;

import ru.krista.tour.model.data.persistence.entities.UserTour;
import ru.krista.tour.model.data.persistence.queryUtils.IColumnFilter;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

public class FilterByUserId implements IColumnFilter<UserTour> {
    public String targetValue;
    public Path<Object> rootColumnValue (Root<UserTour> rootEntity) {
        return rootEntity.get("userId");
    }
    public Object targetColumnValue () {
        return targetValue;
    }

    public FilterByUserId (String targetValue) {
        this.targetValue = targetValue;
    }
}
