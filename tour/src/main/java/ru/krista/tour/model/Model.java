package ru.krista.tour.model;

import ru.krista.tour.controller.domains.IModal;
import ru.krista.tour.model.data.dao.IProvider;
import ru.krista.tour.model.data.dao.tourDao.TourDao;
import ru.krista.tour.model.data.dao.sessionDao.UserDao;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;

/*
 ** отвечает за соединение с БД и транзакции
 */
public class Model implements IModal {
    // entity manager, управляемый контейнером
    @PersistenceContext(name = "tour-integration")
    public EntityManager entityManager;
    @Resource
    public UserTransaction userTransaction;

    IProvider provider;

    // перенести в dao
    @Override
    public void openGateway() {
        try {
            userTransaction.begin();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (NotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void closeGateway() {
        try {
            userTransaction.commit();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserDao getUserDao() {
        return null;
    }

    @Override
    public TourDao getTouDao() {
        return null;
    }

 /*   public CourseDao getTourDao (TourDo tourDo) {
        return new CourseDao<TourDo, Tour, HibernateGateway>(tourDo, new HibernateGateway());
    }
    public CourseDao getUserTourDao (UserTourDo userTourDo) {
        return new CourseDao(userTourDo, new HibernateGateway(), this.tourEntityName, );
    }*/



    /* public List<UserTour> findByRelationType(String userId,  String relationType) {
       try {
           CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
           CriteriaQuery<Tour> query = criteriaBuilder.createQuery(Tour.class);
           Root<Tour> root = query.from(Tour.class);
           query.where(criteriaBuilder.isTrue(root.get("userId")));
           return manager.createQuery(query).getResultList();
       } catch (Exception e) {
           System.out.println(e.getMessage());
           return null;
       }
   }*/



}
