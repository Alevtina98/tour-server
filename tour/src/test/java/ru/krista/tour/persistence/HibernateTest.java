package ru.krista.tour.persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import java.util.HashMap;
import java.util.Map;

@Named("test")
public class HibernateTest {
    protected static EntityManagerFactory factory;
    public EntityManager testManager;

    @Resource
    public UserTransaction testUserTransaction;

    @BeforeClass
    public static void createEntityManagerFactory() {
        Map<String, String> connectionProperties = new HashMap<>();
        connectionProperties.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5433/test");
        connectionProperties.put("javax.persistence.jdbc.user", "postgres");
        connectionProperties.put("javax.persistence.jdbc.password","postgre");
        connectionProperties.put("hibernate.default_schema", "public");
        factory = Persistence.createEntityManagerFactory("tour-test", connectionProperties);
    }
    @AfterClass
    public static void closeEntityManagerFactory() {
        factory.close();
    }
    @Before
    public void beginTransaction() {
        testManager = factory.createEntityManager();
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

}
