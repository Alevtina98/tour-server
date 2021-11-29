package ru.krista.tour.persistence;

import org.junit.*;
import ru.krista.tour.model.data.persistence.Provider;
import ru.krista.tour.model.data.persistence.entities.Tour;

import javax.inject.Named;
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
}
