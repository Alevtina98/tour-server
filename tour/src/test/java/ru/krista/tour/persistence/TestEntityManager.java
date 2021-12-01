package ru.krista.tour.persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

@Named("test")
public class TestEntityManager {
    // entity manager, управляемый приложением
    public static EntityManager testEntityManager;
    protected static EntityManagerFactory factory;

    @BeforeClass
    public static void createEntityManagerFactory() {
        Map<String, String> connectionProperties = new HashMap<>();
        connectionProperties.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5433/test");
        connectionProperties.put("javax.persistence.jdbc.user", "postgres");
        connectionProperties.put("javax.persistence.jdbc.password", "postgre");
        connectionProperties.put("hibernate.default_schema", "public");
        factory = Persistence.createEntityManagerFactory("tour-test", connectionProperties);
        testEntityManager = factory.createEntityManager();
    }

    @AfterClass
    public static void closeEntityManagerFactory() {
        factory.close();
    }

    /*
    @Before
    public void beginTransaction() {
        testEntityManager.getTransaction().begin();
    }
    @After
    public void rollbackTransaction() {
        if (testEntityManager.getTransaction().isActive()) {
            testEntityManager.getTransaction().rollback();
        }

        if (testEntityManager.isOpen()) {
            testEntityManager.close();
        }
    }
    */

}
