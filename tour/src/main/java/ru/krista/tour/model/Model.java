package ru.krista.tour.model;

import ru.krista.tour.controller.domains.IModal;
import ru.krista.tour.model.data.dao.IProvider;
import ru.krista.tour.model.data.dao.tourDao.TourDao;
import ru.krista.tour.model.data.dao.sessionDao.SessionDao;

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
    public boolean openGateway() {
        try {
            userTransaction.begin();
            return true;
        } catch (SystemException e) {
            e.printStackTrace();
            return false;
        } catch (NotSupportedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean closeGateway() {
        try {
            userTransaction.commit();
            return true;
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
            return false;
        } catch (SystemException e) {
            e.printStackTrace();
            return false;
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
            return false;
        } catch (RollbackException e) {
            e.printStackTrace();
            return false;
        }
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


    @Override
    public SessionDao getUserTourDao() {
        return null;
    }

    @Override
    public TourDao getTouDao() {
        return null;
    }
}
