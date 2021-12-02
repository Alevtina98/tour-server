package ru.krista.tour.persistence;

import org.junit.*;
import ru.krista.tour.controller.domains.webApp.user.session.SessionService;
import ru.krista.tour.model.data.dao.tourDao.queryUtils.FilterByGeneral;
import ru.krista.tour.model.data.dao.tourDao.queryUtils.SelectAllFromTour;
import ru.krista.tour.model.data.dao.sessionDao.queryUtils.*;
import ru.krista.tour.model.data.persistence.Provider;
import ru.krista.tour.model.data.persistence.entities.Session;
import ru.krista.tour.model.data.persistence.entities.Tour;
import ru.krista.tour.model.data.persistence.queryUtils.IColumnFilter;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("test")
public class ProviderTest extends TestEntityManager {
    private Provider provider;

    @Before
    public void init() {
        provider = new Provider(testEntityManager);
    }
    @Test
    public void crudTest() {

        // CREATE

        Tour tour = new Tour();
        tour.setName("Test");
        tour.setDesc("");

        testEntityManager.getTransaction().begin();
        Tour createdTour = provider.create(tour).data;
        testEntityManager.getTransaction().commit();
        testEntityManager.detach(tour);

        Assert.assertNotNull(createdTour);
        Assert.assertNotNull(createdTour.getDateCreate());
        Assert.assertNotNull(createdTour.getDateChange());
        Assert.assertEquals(createdTour.getDateChange(),createdTour.getDateCreate());

        Long id = tour.getId();

        // READ

        Tour readingTour = provider.readById(Tour.class, id).data;

        Assert.assertNotNull(readingTour);


        // UPDATE

        String newDesc = "Tour description";
        tour.setDesc(newDesc);

        testEntityManager.getTransaction().begin();
        Tour editedTour =  provider.update(tour).data;
        testEntityManager.getTransaction().commit();
        testEntityManager.detach(tour);

        Assert.assertEquals(editedTour.getDesc(), newDesc);
        Assert.assertNotEquals(editedTour.getDateChange(),editedTour.getDateCreate());

        // DELETE

        testEntityManager.getTransaction().begin();
        provider.delete(Tour.class, id);
        tour = provider.readById(Tour.class, id).data;
        testEntityManager.getTransaction().commit();

        Assert.assertNull(tour);
    }

    @Test
    public void readListTest() {

        Tour tour1 = new Tour();
        tour1.setName("Tour1");
        tour1.setGeneralUser(true);
        Tour tour2 = new Tour();
        tour2.setName("Tour2");
        tour2.setGeneralUser(false);

        testEntityManager.getTransaction().begin();
        Tour pTour1 = provider.create(tour1).data;
        Tour pTour2 = provider.create(tour2).data;
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
        Session pSession1 = provider.create(session1).data;
        Session pSession2 = provider.create(session2).data;
        Session pSession3 = provider.create(session3).data;
        testEntityManager.getTransaction().commit();

        // READ ALL

        List<Tour> tourList = provider.readAll(Tour.class).data;
        List<Session> sessionList = provider.readAll(Session.class).data;

        Assert.assertEquals(tourList.size(), 2);
        Assert.assertEquals(sessionList.size(), 3);

        // READ BY FLAG

        SelectAllFromTour params = new SelectAllFromTour();
        FilterByGeneral filterByGeneral = new FilterByGeneral();

        tourList = provider.readWithFlagFilter(params, filterByGeneral).data;

        Assert.assertEquals(tourList.size(), 1);
        Assert.assertTrue(tourList.get(0).getIsGeneral());

        // READ BY COLUMN

        SelectTourFromUserTour selectTourFromUserTour = new SelectTourFromUserTour();
        FilterByUserId filterByUserId = new FilterByUserId("user2");

        tourList = provider.readWithValueFilter(selectTourFromUserTour,filterByUserId).data;

        Assert.assertEquals(tourList.size(), 1);
        Assert.assertEquals(tourList.get(0).getName(), "Tour1");

        // READ BY COLUMN LIST

        List<IColumnFilter<Session>> filterList = new ArrayList<IColumnFilter<Session>>();
        filterByUserId  = new FilterByUserId("user1");
        FilterByStatus filterByStatus = new FilterByStatus(SessionService.StatusVariant.INTERRUPTED.toString());
        filterList.add(filterByUserId);
        filterList.add(filterByStatus);

        tourList = provider.readWithValueFilter(selectTourFromUserTour, filterList).data;

        Assert.assertEquals(tourList.size(), 1);
        Assert.assertEquals(tourList.get(0).getName(), "Tour2");

        // READ BY KEY AS COLUMN LIST

        SelectAllFromUserTour selectAllFromUserTour = new SelectAllFromUserTour();
        filterList = new ArrayList<IColumnFilter<Session>>();
        filterByUserId  = new FilterByUserId("user1");
        FilterByTourId filterByTourId = new FilterByTourId(pTour1.getId());
        filterList.add(filterByUserId);
        filterList.add(filterByTourId);

        sessionList = provider.readWithValueFilter(selectAllFromUserTour, filterList).data;

        Assert.assertEquals(sessionList.size(), 1);
        Assert.assertEquals(sessionList.get(0).getId(), pSession1.getId());


        testEntityManager.getTransaction().begin();
        provider.delete(Session.class, pSession1.getId());
        provider.delete(Session.class, pSession2.getId());
        provider.delete(Session.class, pSession3.getId());
        provider.delete(Tour.class, pTour1.getId());
        provider.delete(Tour.class, pTour2.getId());
        testEntityManager.getTransaction().commit();

    }
}
