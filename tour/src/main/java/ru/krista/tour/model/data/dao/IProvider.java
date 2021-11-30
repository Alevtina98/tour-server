package ru.krista.tour.model.data.dao;


import ru.krista.tour.model.data.persistence.queryUtils.IColumnFilter;
import ru.krista.tour.model.data.persistence.queryUtils.IColumnFlagFilter;
import ru.krista.tour.model.data.persistence.queryUtils.ISelectParams;

import java.util.List;


public interface IProvider {
    public class SelectFilter {
        String columnName;
        String columnValue;
    }

    public <T> T create(T object);
    public <T> T update(T object);
    public <T> Object delete(Class<T> cls, Long id);

    public <T> T readById(Class<T> cls, Long id);
    public <T> List<T> readAll(Class<T> cls);
    public <TFromEntity, TResultItem> List<TResultItem> readWithFlagFilter(ISelectParams<TFromEntity, TResultItem> queryParams, IColumnFlagFilter<TFromEntity> flagFilter);
    public <TFromEntity, TResultItem> List<TResultItem> readWithValueFilter(ISelectParams<TFromEntity, TResultItem> queryParams, List<IColumnFilter<TFromEntity>> filterList);
}
