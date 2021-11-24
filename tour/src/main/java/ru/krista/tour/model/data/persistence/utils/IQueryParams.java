package ru.krista.tour.model.data.persistence.utils;

public interface IQueryParams <TFromEntity, TResultItem> {
    Class<TFromEntity> classFromEntity ();
    Class<TResultItem> classResult ();
}
