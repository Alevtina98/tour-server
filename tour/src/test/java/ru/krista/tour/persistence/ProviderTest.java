package ru.krista.tour.persistence;

import org.junit.*;
import ru.krista.tour.controller.domains.webApp.user.session.SessionService;
import ru.krista.tour.model.data.dao.tourDao.queryUtils.FilterByGeneral;
import ru.krista.tour.model.data.dao.tourDao.queryUtils.SelectAllFromTour;
import ru.krista.tour.model.data.dao.userDao.queryUtils.*;
import ru.krista.tour.model.data.persistence.entities.Session;
import ru.krista.tour.model.data.persistence.entities.Tour;
import ru.krista.tour.model.data.persistence.queryUtils.IColumnFilter;
import ru.krista.tour.persistence.utils.DbTest;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("test")
public class ProviderTest extends DbTest {

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


        // READ
        Long id = tour.getId();

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
        // READ ALL

        List<Tour> tourList = provider.readAll(Tour.class).data;
        List<Session> sessionList = provider.readAll(Session.class).data;

        Assert.assertEquals(tourList.size(), 2);
        Assert.assertEquals(sessionList.size(), 3);

        // READ BY FLAG

        SelectAllFromTour params = new SelectAllFromTour();
        FilterByGeneral filterByGeneral = new FilterByGeneral();

        tourList = provider.readByFlagFilter(params, filterByGeneral).data;

        Assert.assertEquals(tourList.size(), 1);
        Assert.assertTrue(tourList.get(0).getIsGeneral());

        // READ BY COLUMN

        SelectTourFromSession selectTourFromSession = new SelectTourFromSession();
        FilterByUserId filterByUserId = new FilterByUserId("user2");

        tourList = provider.readWithValueFilter(selectTourFromSession,filterByUserId).data;

        Assert.assertEquals(tourList.size(), 1);
        Assert.assertEquals(tourList.get(0).getName(), "Tour1");

        // READ BY COLUMN LIST

        List<IColumnFilter<Session>> filterList = new ArrayList<IColumnFilter<Session>>();
        filterByUserId  = new FilterByUserId("user1");
        FilterByStatus filterByStatus = new FilterByStatus(SessionService.StatusVariant.INTERRUPTED.toString());
        filterList.add(filterByUserId);
        filterList.add(filterByStatus);

        tourList = provider.readWithValueFilter(selectTourFromSession, filterList).data;

        Assert.assertEquals(tourList.size(), 1);
        Assert.assertEquals(tourList.get(0).getName(), "Tour2");

        // READ BY KEY AS COLUMN LIST

        SelectAllFromSession selectAllFromSession = new SelectAllFromSession();
        filterList = new ArrayList<IColumnFilter<Session>>();
        filterByUserId  = new FilterByUserId("user1");
        FilterByTourId filterByTourId = new FilterByTourId(pTour1.getId());
        filterList.add(filterByUserId);
        filterList.add(filterByTourId);

        sessionList = provider.readWithValueFilter(selectAllFromSession, filterList).data;

        Assert.assertEquals(sessionList.size(), 1);
        Assert.assertEquals(sessionList.get(0).getId(), pSession1.getId());

    }
}
