package com.revature.data;

import com.revature.beans.Role;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RoleHibernateTest {
    private RoleHibernate roleHibernate = new RoleHibernate();

    private Role role = new Role();

    @Test
    @Order(1)
    void getById() {
        role.setRole("user");
        role.setId(1);
        assertEquals(role, roleHibernate.getById(role.getId()));
    }

    @Test
    @Order(2)
    void getAll() {
        assertTrue(roleHibernate.getAll().size() > 0);
    }

}
