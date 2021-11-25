package ru.krista.tour.jre;

import org.junit.*;
import ru.krista.tour.model.data.persistence.Provider;
import ru.krista.tour.model.data.persistence.entities.Tour;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named("test")
public class ProviderTest {
    @Inject
    private Provider provider;

    protected static EntityManagerFactory factory;
    public EntityManager manager;

    @Resource
    public UserTransaction userTransaction;
   @BeforeClass
    public static void createEntityManagerFactory() {
        Map<String, String> connectionProperties = new HashMap<>();
        connectionProperties.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5433/test");
        connectionProperties.put("javax.persistence.jdbc.user", "postgres");
        connectionProperties.put("javax.persistence.jdbc.password","postgre");
        connectionProperties.put("hibernate.default_schema", "Tour");
       factory = Persistence.createEntityManagerFactory("tour", connectionProperties);
    }
    @AfterClass
    public static void closeEntityManagerFactory() {
        factory.close();
    }
    @Before
    public void beginTransaction() {
        manager = factory.createEntityManager();
        //em.getTransaction().begin();
    }
   /* @After
    public void rollbackTransaction() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }

        if (em.isOpen()) {
            em.close();
        }
    }*/


    @Test
   public void readingTest () {
        provider = new Provider();
        provider.entityManager = manager;
       // List<Tour> testTourList = new ArrayList<Tour>();
        Tour newTour = new Tour("Тестовый", "Какое-то описание");
        Tour tour = provider.create(newTour);
     //   Assert.assertNotNull(tour);

        List<Tour> result = provider.readAll(Tour.class);
        System.out.println(result);
        Assert.assertNotNull(result);
   }



}
