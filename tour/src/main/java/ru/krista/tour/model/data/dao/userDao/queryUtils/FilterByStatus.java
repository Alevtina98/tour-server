package ru.krista.tour.model.data.dao.userDao.queryUtils;

import ru.krista.tour.model.data.persistence.entities.Session;
import ru.krista.tour.model.data.persistence.queryUtils.IColumnFilter;

import javax.faces.bean.ApplicationScoped;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
@ApplicationScoped
public class FilterByStatus implements IColumnFilter<Session> {
    public String targetValue;
    public Path<Object> rootColumnValue (Root<Session> rootEntity) {
        return rootEntity.get("status");
    };
    public Object targetColumnValue () {
        return targetValue;
    }

    public FilterByStatus (String targetValue) {
        this.targetValue = targetValue;
    }
}