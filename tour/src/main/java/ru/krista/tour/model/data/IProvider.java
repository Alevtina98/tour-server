package ru.krista.tour.model.data;


import ru.krista.tour.model.data.persistence.utils.IColumnFilter;
import ru.krista.tour.model.data.persistence.utils.IColumnFlagFilter;
import ru.krista.tour.model.data.persistence.utils.IQueryParams;

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
    public <TFromEntity, TResultItem> List<TResultItem> readWithFlagFilter(IQueryParams<TFromEntity, TResultItem> queryParams, IColumnFlagFilter<TFromEntity> flagFilter);
    public <TFromEntity, TResultItem> List<TResultItem> readWithValueFilter(IQueryParams<TFromEntity, TResultItem> queryParams, List<IColumnFilter<TFromEntity>> filterList);
}
