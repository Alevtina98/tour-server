package ru.krista.tour.model.data.dao.tourDao.queryUtils;

import ru.krista.tour.model.data.persistence.queryUtils.IColumnFlagFilter;
import ru.krista.tour.model.data.persistence.entities.Tour;

import javax.faces.bean.ApplicationScoped;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
@ApplicationScoped
public class FilterByGeneral implements IColumnFlagFilter<Tour> {
    public Expression<Boolean> rootColumnFlagValue (Root<Tour> rootEntity) {
        return rootEntity.get("isGeneralUser");
    };
}
