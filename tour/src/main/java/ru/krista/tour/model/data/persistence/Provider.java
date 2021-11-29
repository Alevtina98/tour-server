package ru.krista.tour.model.data.persistence;

import ru.krista.tour.model.data.IProvider;
import ru.krista.tour.model.data.persistence.entities.RootKey;
import ru.krista.tour.model.data.persistence.utils.IColumnFilter;
import ru.krista.tour.model.data.persistence.utils.IColumnFlagFilter;
import ru.krista.tour.model.data.persistence.utils.IQueryParams;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.*;
import java.util.List;

public class Provider implements IProvider {
    public EntityManager entityManager;

    public Provider(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public <TEntity> Object delete(Class<TEntity> entityClass, Long id) {
        try {
            // userTransaction.begin();
            TEntity entity = readById(entityClass, id);
            if (entity != null) {
                entityManager.remove(entity);
            }
            entityManager.flush();
            // userTransaction.commit();
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    @Transactional
    public <TEntity> TEntity create(TEntity object) {
        try {
            entityManager.persist(object);
            return object;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    @Transactional
    public <TEntity> TEntity update(TEntity object) {
        try {
            TEntity entity = entityManager.merge(object);
            entityManager.persist(entity);
            return entity;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public <TEntity> List<TEntity> readAll(Class<TEntity> cls) {
        if (entityManager == null) {
            System.err.println("entity manager is null");
            return null;
        }
        try {
            String query = String.format("FROM %s", cls.getName());
            return (List<TEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public <T> T readById(Class<T> cls, Long id) {
        try {
            return entityManager.find(cls, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public <TFromEntity, TResultItem> List<TResultItem> readWithFlagFilter(IQueryParams<TFromEntity, TResultItem> queryParams, IColumnFlagFilter<TFromEntity> flagFilter) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<TResultItem> query = criteriaBuilder.createQuery(queryParams.classResult());
            Root<TFromEntity> rootEntity = query.from(queryParams.classFromEntity());
            query.where(criteriaBuilder.isTrue(flagFilter.rootColumnFlagValue(rootEntity)));
            TypedQuery<TResultItem> tq = entityManager.createQuery(query);
            return tq.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public <TFromEntity, TResultItem> List<TResultItem> readWithValueFilter(IQueryParams<TFromEntity, TResultItem> queryParams, List<IColumnFilter<TFromEntity>> valueFilterList) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<TResultItem> query = criteriaBuilder.createQuery(queryParams.classResult());
            Root<TFromEntity> rootEntity = query.from(queryParams.classFromEntity());
            query.where(
                    criteriaBuilder.and(
                            valueFilterList.stream().map(
                                    filter -> criteriaBuilder.equal(filter.rootColumnValue(rootEntity), filter.targetColumnValue())
                            ).toArray(Predicate[]::new)
                    )
            );
            TypedQuery<TResultItem> tq = entityManager.createQuery(query);
            return tq.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
