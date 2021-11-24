package ru.krista.tour.model.data.dataAccessObjects;

import ru.krista.tour.controller.domains.webApp.user.session.SessionBo;
import ru.krista.tour.model.data.persistence.entities.Tour.Tour;
import ru.krista.tour.model.data.persistence.entities.UserTour.UserTour;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDao {
    @PersistenceContext(name="tour")
    private EntityManager manager;

    private SessionBo buildSessionBo () {
        return  null;
    }

    public List<Tour> findToursByRelationType(String userId,  String relationType) {
          try {
              CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
              CriteriaQuery<Tour> query = criteriaBuilder.createQuery(Tour.class);
              Root<UserTour> userTour = query.from(UserTour.class);
              query.select(userTour.get("tour"));
              query.where(criteriaBuilder.and(
                      criteriaBuilder.equal(userTour.get("userId"), userId),
                      criteriaBuilder.equal(userTour.get("status"),relationType)));
              TypedQuery<Tour> tq = manager.createQuery(query);
              return tq.getResultList();
          } catch (Exception e) {
              System.out.println(e.getMessage());
              return null;
          }
   }

    public List<Tour> findToursGeneral() {
        try {
            CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
            CriteriaQuery<Tour> query = criteriaBuilder.createQuery(Tour.class);
            Root<Tour> root = query.from(Tour.class);
            query.where(criteriaBuilder.isTrue(root.get("isGeneralUser")));
            return manager.createQuery(query).getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public List<Tour> findTours(String userId) {
        try {
            CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
            CriteriaQuery<Tour> query = criteriaBuilder.createQuery(Tour.class);
            Root<UserTour> userTour = query.from(UserTour.class);
            query.select(userTour.get("tour"));
            query.where(criteriaBuilder.equal(userTour.get("user"), userId));
            TypedQuery<Tour> tq = manager.createQuery(query);
            return tq.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public List<UserTour> findSessions(String userId, Long tourId) {
        try {
            CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
            CriteriaQuery<UserTour> query = criteriaBuilder.createQuery(UserTour.class);
            Root<UserTour> userTour = query.from(UserTour.class);
            query.where(criteriaBuilder.and(
                    criteriaBuilder.equal(userTour.get("userId"), userId),
                    criteriaBuilder.equal(userTour.get("tour").get("id"),tourId)));
            TypedQuery<UserTour> tq = manager.createQuery(query);
            return tq.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
