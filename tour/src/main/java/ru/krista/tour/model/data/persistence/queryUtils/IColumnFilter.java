package ru.krista.tour.model.data.persistence.queryUtils;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

public interface IColumnFilter <TEntity> {
    public Path<Object> rootColumnValue (Root<TEntity> rootEntity);
    public Object targetColumnValue ();
}
