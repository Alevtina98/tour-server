package ru.krista.tour.model.data.persistence.queryUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

public interface IColumnFlagFilter<TEntity>{
    public Expression<Boolean> rootColumnFlagValue (Root<TEntity> rootEntity);
}
