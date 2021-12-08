package ru.krista.tour.model.data;

import ru.krista.tour.model.IModal;
import ru.krista.tour.model.data.dao.IProvider;
import ru.krista.tour.model.data.dao.tourDao.TourDao;
import ru.krista.tour.model.data.dao.userDao.UserDao;
import ru.krista.tour.model.data.persistence.Provider;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import java.util.logging.Logger;

/*
 ** отвечает за соединение с БД и транзакции
 */
@ApplicationScoped
public class Model implements IModal {
    // entity manager, управляемый контейнером
    @PersistenceContext(name = "integration-data-source")
    public EntityManager entityManager;

    @Inject
    public UserTransaction userTransaction;

    IProvider provider;


    @PostConstruct
    public void onInit() {
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
