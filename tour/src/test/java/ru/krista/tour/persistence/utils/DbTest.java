package ru.krista.tour.persistence.utils;

import org.junit.After;
import org.junit.Before;
import ru.krista.tour.controller.domains.webApp.user.session.SessionService;
import ru.krista.tour.model.data.persistence.Provider;
import ru.krista.tour.model.data.persistence.entities.Session;
import ru.krista.tour.model.data.persistence.entities.Tour;

import javax.inject.Named;

@Named("db-test")
public class DbTest extends EntityManagerTest {
    protected Provider provider;

    protected Tour pTour1;
    protected Tour pTour2;
    protected Session pSession1;
    protected Session pSession2;
    protected Session pSession3;

    @Before
    public void init() {
        provider = new Provider(testEntityManager);

        Tour tour1 = new Tour();
        tour1.setName("Tour1");
        tour1.setGeneralUser(true);
        Tour tour2 = new Tour();
        tour2.setName("Tour2");
        tour2.setGeneralUser(false);

        testEntityManager.getTransaction().begin();
        pTour1 = provider.create(tour1).data;
        pTour2 = provider.create(tour2).data;
        testEntityManager.getTransaction().commit();

        Session session1 = new Session();
        session1.setUserId("user1");
        session1.setTour(pTour1);
        session1.setStatus(SessionService.StatusVariant.DELAYED.toString());
        Session session2 = new Session();
        session2.setUserId("user2");
        session2.setTour(pTour1);
        session2.setStatus(SessionService.StatusVariant.INTERRUPTED.toString());
        Session session3 = new Session();
        session3.setUserId("user1");
        session3.setTour(pTour2);
        session3.setStatus(SessionService.StatusVariant.INTERRUPTED.toString());

        testEntityManager.getTransaction().begin();
        pSession1 = provider.create(session1).data;
        pSession2 = provider.create(session2).data;
        pSession3 = provider.create(session3).data;
        testEntityManager.getTransaction().commit();
    }
    @After
    public void clearDb() {
        testEntityManager.getTransaction().begin();
        provider.delete(Session.class, pSession1.getId());
        provider.delete(Session.class, pSession2.getId());
        provider.delete(Session.class, pSession3.getId());
        provider.delete(Tour.class, pTour1.getId());
        provider.delete(Tour.class, pTour2.getId());
        testEntityManager.getTransaction().commit();
    }
}
