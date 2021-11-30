package ru.krista.tour.persistence;

import org.junit.*;
import ru.krista.tour.controller.domains.webApp.user.session.SessionBo;
import ru.krista.tour.model.data.dao.tourDao.queryUtils.FilterByGeneral;
import ru.krista.tour.model.data.dao.tourDao.queryUtils.SelectAllFromTour;
import ru.krista.tour.model.data.dao.userDao.queryUtils.*;
import ru.krista.tour.model.data.persistence.Provider;
import ru.krista.tour.model.data.persistence.entities.Tour;
import ru.krista.tour.model.data.persistence.entities.UserTour;
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
        Tour createdTour = provider.create(tour);
        testEntityManager.getTransaction().commit();
        testEntityManager.detach(tour);

        Assert.assertNotNull(createdTour);
        Assert.assertNotNull(createdTour.getDateCreate());
        Assert.assertNotNull(createdTour.getDateChange());
        Assert.assertEquals(createdTour.getDateChange(),createdTour.getDateCreate());

        Long id = tour.getId();

        // READ

        Tour readingTour = provider.readById(Tour.class, id);

        Assert.assertNotNull(readingTour);


        // UPDATE

        String newDesc = "Tour description";
        tour.setId(id);
        tour.setDesc(newDesc);

        testEntityManager.getTransaction().begin();
        Tour editedTour =  provider.update(tour);
        testEntityManager.getTransaction().commit();
        testEntityManager.detach(tour);

        Assert.assertEquals(editedTour.getDesc(), newDesc);
        Assert.assertNotEquals(editedTour.getDateChange(),editedTour.getDateCreate());

        // DELETE

        testEntityManager.getTransaction().begin();
        provider.delete(Tour.class, id);
        tour = provider.readById(Tour.class, id);
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
        Tour pTour1 = provider.create(tour1);
        Tour pTour2 = provider.create(tour2);
        testEntityManager.getTransaction().commit();

        UserTour userTour1 = new UserTour();
        userTour1.setUserId("user1");
        userTour1.setTour(pTour1);
        userTour1.setStatus(SessionBo.StatusVariant.DELAYED.toString());
        UserTour userTour2 = new UserTour();
        userTour2.setUserId("user2");
        userTour2.setTour(pTour1);
        userTour2.setStatus(SessionBo.StatusVariant.INTERRUPTED.toString());
        UserTour userTour3 = new UserTour();
        userTour3.setUserId("user1");
        userTour3.setTour(pTour2);
        userTour3.setStatus(SessionBo.StatusVariant.INTERRUPTED.toString());

        testEntityManager.getTransaction().begin();
        UserTour pUserTour1 = provider.create(userTour1);
        UserTour pUserTour2 = provider.create(userTour2);
        UserTour pUserTour3 = provider.create(userTour3);
        testEntityManager.getTransaction().commit();

        // READ ALL

        List<Tour> tourList = provider.readAll(Tour.class);
        List<UserTour> userTourList = provider.readAll(UserTour.class);

        Assert.assertEquals(tourList.size(), 2);
        Assert.assertEquals(userTourList.size(), 3);

        // READ BY FLAG

        SelectAllFromTour params = new SelectAllFromTour();
        FilterByGeneral filterByGeneral = new FilterByGeneral();

        tourList = provider.readWithFlagFilter(params, filterByGeneral);

        Assert.assertEquals(tourList.size(), 1);
        Assert.assertTrue(tourList.get(0).isGeneralUser());

        // READ BY COLUMN

        SelectTourFromUserTour selectTourFromUserTour = new SelectTourFromUserTour();
        FilterByUserId filterByUserId = new FilterByUserId("user2");

        tourList = provider.readWithValueFilter(selectTourFromUserTour,filterByUserId);

        Assert.assertEquals(tourList.size(), 1);
        Assert.assertEquals(tourList.get(0).getName(), "Tour1");

        // READ BY COLUMN LIST

        List<IColumnFilter<UserTour>> filterList = new ArrayList<IColumnFilter<UserTour>>();
        filterByUserId  = new FilterByUserId("user1");
        FilterByStatus filterByStatus = new FilterByStatus(SessionBo.StatusVariant.INTERRUPTED.toString());
        filterList.add(filterByUserId);
        filterList.add(filterByStatus);

        tourList = provider.readWithValueFilter(selectTourFromUserTour, filterList);

        Assert.assertEquals(tourList.size(), 1);
        Assert.assertEquals(tourList.get(0).getName(), "Tour2");

        // READ BY KEY AS COLUMN LIST

        SelectAllFromUserTour selectAllFromUserTour = new SelectAllFromUserTour();
        filterList = new ArrayList<IColumnFilter<UserTour>>();
        filterByUserId  = new FilterByUserId("user1");
        FilterByTourId filterByTourId = new FilterByTourId(pTour1.getId());
        filterList.add(filterByUserId);
        filterList.add(filterByTourId);

        userTourList = provider.readWithValueFilter(selectAllFromUserTour, filterList);

        Assert.assertEquals(userTourList.size(), 1);
        Assert.assertEquals(userTourList.get(0).getId(), pUserTour1.getId());


        testEntityManager.getTransaction().begin();
        provider.delete(UserTour.class, pUserTour1.getId());
        provider.delete(UserTour.class, pUserTour2.getId());
        provider.delete(UserTour.class, pUserTour3.getId());
        provider.delete(Tour.class, pTour1.getId());
        provider.delete(Tour.class, pTour2.getId());
        testEntityManager.getTransaction().commit();

    }
}
