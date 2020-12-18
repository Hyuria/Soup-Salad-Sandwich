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
    void add() {
        role.setRole("Test");
        Role retRole = roleHibernate.add(role);
        role.setId(retRole.getId());
        assertEquals(role, retRole);
    }

    @Test
    @Order(2)
    void getById() {
        assertEquals(role, roleHibernate.getById(role.getId()));
    }

    @Test
    @Order(3)
    void getAll() {
        assertTrue(roleHibernate.getAll().size() > 0);
    }

    @Test
    @Order(4)
    void update() {
        role.setRole("Update");
        roleHibernate.update(role);
        assertEquals(role, roleHibernate.getById(role.getId()));
    }

    @Test
    @Order(5)
    void delete() {
        roleHibernate.delete(role);
        assertTrue(roleHibernate.getById(role.getId()) == null);
    }
}
