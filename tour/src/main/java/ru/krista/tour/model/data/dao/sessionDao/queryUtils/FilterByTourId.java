package ru.krista.tour.model.data.dao.sessionDao.queryUtils;

import ru.krista.tour.model.data.persistence.entities.Session;
import ru.krista.tour.model.data.persistence.queryUtils.IColumnFilter;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

public class FilterByTourId implements IColumnFilter<Session> {
    public Number targetValue;
    public Path<Object> rootColumnValue (Root<Session> rootEntity) {
        return rootEntity.get("tour").get("id");
    }
    public Object targetColumnValue () {
        return targetValue;
    }

    public FilterByTourId (Number targetValue) {
        this.targetValue = targetValue;
    }
}