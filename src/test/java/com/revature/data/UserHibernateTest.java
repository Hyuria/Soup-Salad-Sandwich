package com.revature.data;

import com.revature.beans.Status;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserHibernateTest {
    private StatusHibernate statusHibernate = new StatusHibernate();

    private Status status = new Status();

    @Test
    @Order(2)
    void getById() {
        status.setId(1);
        status.setName("admin pending");
        assertEquals(status, statusHibernate.getById(status.getId()));
    }

    @Test
    @Order(3)
    void getAll() {
        assertTrue(statusHibernate.getAll().size() > 0);
    }
}