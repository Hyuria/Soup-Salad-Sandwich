package com.revature.services;

import org.junit.jupiter.api.Test;

import com.revature.beans.Role;
import com.revature.data.RoleDAO;
import com.revature.data.RoleHibernate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

class RoleServiceImplTest {
	RoleDAO roleDAO = new RoleHibernate();
	Role r = new Role();
	Set<Role> roleSet = new HashSet<Role>();

    @Test
    void getRoleById() {
    	r = roleDAO.getById(1);
    	assertEquals("user",r.getRole());
    }

    @Test
    void getAll() {
    	roleSet = roleDAO.getAll();
    	assertTrue(roleSet.size()>0);
    }
}