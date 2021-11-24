package ru.krista.tour.model;

import ru.krista.tour.controller.domains.IModal;
import ru.krista.tour.model.data.IProvider;
import ru.krista.tour.model.data.dao.TourDao;


public class Model implements IModal {
    IProvider provider;

    public Model() {

    }
    public void start (){

    }
    public void finish () {

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
    public void openGateway() {

    }

    @Override
    public void closeGateway() {

    }

    @Override
    public void getUserTourDao() {

    }

    @Override
    public TourDao getTouDao() {
        return null;
    }
}
