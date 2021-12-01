package ru.krista.tour.model;

import ru.krista.tour.controller.domains.IModal;
import ru.krista.tour.model.data.dao.IProvider;
import ru.krista.tour.model.data.dao.tourDao.TourDao;
import ru.krista.tour.model.data.dao.sessionDao.UserDao;
import ru.krista.tour.model.data.persistence.Provider;

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

    public Model () {
        provider = new Provider(entityManager);
    }

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
        return new UserDao(provider);
    }

    @Override
    public TourDao getTouDao() {
        return new TourDao(provider);
    }

}
