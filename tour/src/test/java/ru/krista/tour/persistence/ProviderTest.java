package ru.krista.tour.persistence;

import org.junit.*;
import ru.krista.tour.model.data.persistence.Provider;
import ru.krista.tour.model.data.persistence.entities.Tour;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("test")
public class ProviderTest extends HibernateTest {
    @Inject
    private Provider provider;

    @Test
    public void readingTest() {
        provider = new Provider();
        provider.entityManager = testManager;
        // List<Tour> testTourList = new ArrayList<Tour>();
        Tour newTour = new Tour("Тестовый", "Какое-то описание");
        Tour tour = provider.create(newTour);
        //   Assert.assertNotNull(tour);

        List<Tour> result = provider.readAll(Tour.class);
        System.out.println(result);
        Assert.assertNotNull(result);
    }
}
