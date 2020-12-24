package com.revature.services;

import org.junit.jupiter.api.Test;

import com.revature.beans.User;
import com.revature.data.RoleDAO;
import com.revature.data.RoleHibernate;
import com.revature.data.UserDAO;
import com.revature.data.UserHibernate;
import com.revature.exception.NonUniqueUsernameException;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

class UserServiceImplTest {
    UserDAO userDAO = new UserHibernate();
    RoleDAO roleDAO = new RoleHibernate();
    User u = new User();
    
    Set<User> userSet = new HashSet<User>();

    @Test
    void addUser() throws NonUniqueUsernameException {
    	u.setUsername("jessie");
    	u.setPassword("jessie");	
    	u.setRole(roleDAO.getById(1));
    	u = userDAO.add(u);
    	assertEquals("jessie", u.getUsername()); 
        assertEquals("jessie", u.getPassword());
        assertEquals(1, u.getRole().getId());
    }

    @Test
    void getUserById() {
        u = userDAO.getById(1);
        assertEquals("jakeem", u.getUsername());
    }

    @Test
    void getAllUsers() {
        userSet = userDAO.getAll();
        assertTrue(userSet.size()>0);
    }

    @Test
    void getUserByUsername() {
    	u = userDAO.getByUsername("caillou");
    	assertEquals("caillou", u.getUsername());
    }

    @Test
    void updateUser() {
        u.setId(22);
        u.setUsername("tracey");
        u.setPassword("tracey");
        u.setRole(roleDAO.getById(1));
        userDAO.update(u);
        assertEquals("tracey", userDAO.getById(22).getUsername());
        assertEquals("tracey", userDAO.getById(22).getPassword());
        assertEquals(1, userDAO.getById(22).getRole());
    }

    @Test
    void deleteUser() {
     	u.setId(22);
    	userDAO.delete(u);
    	assertFalse(userDAO.getById(22) !=null);  	
    }
}