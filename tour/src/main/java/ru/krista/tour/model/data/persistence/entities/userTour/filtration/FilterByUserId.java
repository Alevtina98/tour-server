package ru.krista.tour.model.data.persistence.entities.userTour.filtration;

import ru.krista.tour.model.data.persistence.entities.userTour.UserTour;
import ru.krista.tour.model.data.persistence.utils.IColumnFilter;

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
}
