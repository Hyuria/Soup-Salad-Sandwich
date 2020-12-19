package com.revature.data;

import com.revature.beans.Status;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StatusHibernateTest {
    private StatusHibernate statusHibernate = new StatusHibernate();

    private Status status = new Status();

    @Test
    @Order(1)
    void add() {
        status.setName("Test");
        Status retStatus = statusHibernate.add(status);
        status.setId(retStatus.getId());
        assertEquals(status, retStatus);
    }

    @Test
    @Order(2)
    void getById() {
        assertEquals(status, statusHibernate.getById(status.getId()));
    }

    @Test
    @Order(3)
    void getAll() {
        assertTrue(statusHibernate.getAll().size() > 0);
    }

    @Test
    @Order(4)
    void update() {
        status.setName("Update");
        statusHibernate.update(status);
        assertEquals(status, statusHibernate.getById(status.getId()));
    }

    @Test
    @Order(5)
    void delete() {
        statusHibernate.delete(status);
        assertTrue(statusHibernate.getById(status.getId()) == null);
    }
}
