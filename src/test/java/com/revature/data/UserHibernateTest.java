package com.revature.data;

import com.revature.beans.User;
import com.revature.exception.NonUniqueUsernameException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserHibernateTest {
    private UserHibernate userHibernate = new UserHibernate();
    private RoleHibernate roleHibernate = new RoleHibernate();

    private User user = new User();

    @Test
    @Order(1)
    void add() throws NonUniqueUsernameException {
        user.setUsername("test");
        user.setPassword("pwd");
        user.setRole(roleHibernate.getById(1));
        User retUser = userHibernate.add(user);
        user.setId(retUser.getId());
        assertEquals(user, retUser);
    }

    @Test
    @Order(2)
    void getById() {
        assertEquals(user, userHibernate.getById(user.getId()));
    }

    @Test
    @Order(3)
    void getByUsername() {
        assertEquals(user, userHibernate.getByUsername(user.getUsername()));
    }

    @Test
    @Order(4)
    void getAll() {
        assertTrue(userHibernate.getAll().size() > 0);
    }

    @Test
    @Order(5)
    void update() {
        user.setUsername("test_update");
        userHibernate.update(user);
        assertEquals(user, userHibernate.getById(user.getId()));
    }

    @Test
    @Order(6)
    void delete() {
        userHibernate.delete(user);
        assertTrue(userHibernate.getById(user.getId()) == null);
    }
}