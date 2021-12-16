package ru.krista.tour.model.data.persistence;

import ru.krista.tour.Dto;
import ru.krista.tour.model.data.Model;
import ru.krista.tour.model.data.dao.IProvider;
import ru.krista.tour.model.data.persistence.queryUtils.IColumnFilter;
import ru.krista.tour.model.data.persistence.queryUtils.IColumnFlagFilter;
import ru.krista.tour.model.data.persistence.queryUtils.ISelectParams;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.*;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.logging.Logger;

public class Provider implements IProvider {
    public EntityManager entityManager;

    public Provider(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public <TEntity> Dto<TEntity> create(TEntity entity) {
        Dto<TEntity> result =  new Dto<>(null);
        try {
            entityManager.persist(entity);
            result.setData(entity);
        } catch (Exception e) {
            result.setError(e.getMessage());
        }
        return result;
    }

    @Override
    public <TEntity>  Dto<TEntity> readById(Class<TEntity> cls, Object id) {
        Dto<TEntity> result =  new Dto<>(null);
        try {
            TEntity entity = entityManager.find(cls, id);
            result.setData(entity);
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.addErrorMsg("Provider: Ошибка при чтении данных");
           // throw new NotFoundException();

        }
        return result;
    }

    @Override
    @Transactional
    public <TEntity>  Dto<TEntity> update(TEntity object) {
        Dto<TEntity> result =  new Dto<>(null);
        try {
            TEntity entity = entityManager.merge(object);
            entityManager.persist(entity);
            result.setData(entity);
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.addErrorMsg("Provider: Ошибка при обновлении данных");
            //throw new NotFoundException();

        }
        return result;

    }

    @Override
    @Transactional
    public <TEntity>  Dto<TEntity> delete(Class<TEntity> entityClass, Object id) {
        Dto<TEntity> result =  new Dto<>(null);
        try {
            Dto<TEntity> readResult = readById(entityClass, id);
            if (readResult.status == Dto.Status.ok) {
                entityManager.remove(readResult.data);
                entityManager.flush();
                result.setData(readResult.data);
            } else {
                result = readResult;
                result.addErrorMsg("Provider: Ошибка при удалении данных");
                //throw new NotFoundException();

            }
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.addErrorMsg("Provider: Ошибка при удалении данных");
            //throw new NotFoundException();

        }
        return result;
    }

    @Override
    public <TEntity> Dto<List<TEntity>> readAll(Class<TEntity> cls) {
        Dto<List<TEntity>> result =  new Dto<>(null);
        try {
            String query = String.format("FROM %s", cls.getName());
            List<TEntity> entityList = entityManager.createQuery(query).getResultList();
            result.setData(entityList);
        } catch (Exception e) {

            result.setError(e.getMessage());
            result.addErrorMsg("Provider: Ошибка при чтении полного списка данных");
            //throw new NotFoundException();

        }
        return result;
    }

    @Override
    public <TFromEntity, TResultItem> Dto<List<TResultItem>> readByFlagFilter(ISelectParams<TFromEntity, TResultItem> selectParams, IColumnFlagFilter<TFromEntity> flagFilter) {
        Dto<List<TResultItem>> result =  new Dto<>(null);
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<TResultItem> query = criteriaBuilder.createQuery(selectParams.classResult());
            Root<TFromEntity> rootEntity = query.from(selectParams.classFromEntity());
            if (selectParams.classFromEntity() != selectParams.classResult()) {
                query.select(selectParams.result(rootEntity));
            }
            query.where(criteriaBuilder.isTrue(flagFilter.rootColumnFlagValue(rootEntity)));
            TypedQuery<TResultItem> tq = entityManager.createQuery(query);
            result.setData(tq.getResultList());
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.addErrorMsg("Provider: Ошибка при чтении списка данных, отфильтрованных по указанному флагу");
           // throw new NotFoundException();

        }
        return result;
    }
    @Override
    public <TFromEntity, TResultItem> Dto<List<TResultItem>> readWithValueFilter(ISelectParams<TFromEntity, TResultItem> selectParams, IColumnFilter<TFromEntity> valueFilter) {
        Dto<List<TResultItem>> result =  new Dto<>(null);
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<TResultItem> query = criteriaBuilder.createQuery(selectParams.classResult());
            Root<TFromEntity> rootEntity = query.from(selectParams.classFromEntity());
            if (selectParams.classFromEntity() != selectParams.classResult()) {
                query.select(selectParams.result(rootEntity));
            }
            query.where(criteriaBuilder.and(criteriaBuilder.equal(valueFilter.rootColumnValue(rootEntity), valueFilter.targetColumnValue())));
            TypedQuery<TResultItem> tq = entityManager.createQuery(query);
            result.setData(tq.getResultList());
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.addErrorMsg("Provider: Ошибка при чтении списка данных, отфильтрованных по указанному полю");
            //throw new NotFoundException();

        }
        return result;
    }

    @Override
    public <TFromEntity, TResultItem> Dto<List<TResultItem>> readWithValueFilter(ISelectParams<TFromEntity, TResultItem> selectParams, List<IColumnFilter<TFromEntity>> valueFilterList) {
        Dto<List<TResultItem>> result =  new Dto<>(null);
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<TResultItem> query = criteriaBuilder.createQuery(selectParams.classResult());
            Root<TFromEntity> rootEntity = query.from(selectParams.classFromEntity());
            if (selectParams.classFromEntity() != selectParams.classResult()) {
                query.select(selectParams.result(rootEntity));
            }
            query.where(
                    criteriaBuilder.and(
                            valueFilterList.stream().map(
                                    filter -> criteriaBuilder.equal(filter.rootColumnValue(rootEntity), filter.targetColumnValue())
                            ).toArray(Predicate[]::new)
                    )
            );
            TypedQuery<TResultItem> tq = entityManager.createQuery(query);
            result.setData(tq.getResultList());
        } catch (Exception e) {
            result.setError(e.getMessage());
            result.addErrorMsg("Provider: Ошибка при чтении списка данных, отфильтрованных по указанным полям");
           // throw new NotFoundException();

        }
        return result;
    }
}
