package ru.krista.tour.persistence.indegrationTests;


import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class TestEntityManager {
    @PersistenceContext(name = "integration-data-source")
    public EntityManager entityManager;

    @Before
    public void initEm () {
        entityManager = Persistence.createEntityManagerFactory("integration-data-source").createEntityManager();
    }
}
