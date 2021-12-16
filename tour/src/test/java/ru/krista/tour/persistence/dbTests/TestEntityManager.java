package ru.krista.tour.persistence.dbTests;

import org.junit.BeforeClass;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

@Named("test-persistence")
public class TestEntityManager {
    // entity manager, управляемый приложением
    public static EntityManager entityManager;
    public static EntityManagerFactory factory;

    @BeforeClass
    public static void createEntityManagerFactory() {
        Map<String, String> connectionProperties = new HashMap<>();
        connectionProperties.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5433/test");
        connectionProperties.put("javax.persistence.jdbc.user", "postgres");
        connectionProperties.put("javax.persistence.jdbc.password", "postgre");
        connectionProperties.put("hibernate.default_schema", "public");
        factory = javax.persistence.Persistence.createEntityManagerFactory( "test-data-source", connectionProperties);
        entityManager = factory.createEntityManager();
    }

    /*@AfterClass
    public static void closeEntityManagerFactory() {
        factory.close();
    }*/

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
