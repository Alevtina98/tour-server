package ru.krista.tour.model.data.persistence.queryUtils;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

public interface ISelectParams<TFromEntity, TResultItem> {
    Class<TFromEntity> classFromEntity ();
    Class<TResultItem> classResult ();
    Path<TResultItem> result(Root<TFromEntity> fromEntity);
}
