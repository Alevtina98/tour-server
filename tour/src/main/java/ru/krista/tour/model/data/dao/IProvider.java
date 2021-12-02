package ru.krista.tour.model.data.dao;


import ru.krista.tour.Dto;
import ru.krista.tour.model.data.persistence.queryUtils.IColumnFilter;
import ru.krista.tour.model.data.persistence.queryUtils.IColumnFlagFilter;
import ru.krista.tour.model.data.persistence.queryUtils.ISelectParams;

import java.util.List;


public interface IProvider {
    public <T> Dto<T> create(T object);
    public <T> Dto<T> update(T object);
    public <T> Dto<T> delete(Class<T> cls, Long id);

    public <T> Dto<T> readById(Class<T> cls, Long id);
    public <T> Dto<List<T>> readAll(Class<T> cls);
    public <TFromEntity, TResultItem> Dto<List<TResultItem>> readWithFlagFilter(ISelectParams<TFromEntity, TResultItem> queryParams, IColumnFlagFilter<TFromEntity> flagFilter);
    public <TFromEntity, TResultItem> Dto<List<TResultItem>> readWithValueFilter(ISelectParams<TFromEntity, TResultItem> queryParams, List<IColumnFilter<TFromEntity>> filterList);
}
