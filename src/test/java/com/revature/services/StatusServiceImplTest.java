package com.revature.services;

import org.junit.jupiter.api.Test;

import com.revature.beans.Status;
import com.revature.data.StatusDAO;
import com.revature.data.StatusHibernate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

class StatusServiceImplTest {
	Status s = new Status();
	StatusDAO statusDAO = new StatusHibernate();
	Set<Status> statusSet = new HashSet<>();

    @Test
    void addStatus() {
    }

    @Test
    void getStatusById() {
    	s = statusDAO.getById(1);
    	assertEquals("admin pending",s.getName());
    }

    @Test
    void getAll() {
    	statusSet = statusDAO.getAll();
    	assertTrue(statusSet.size()>0);
    }

    @Test
    void updateStatus() {
    	s.setId(5);
    	s.setName("temp2");
    	statusDAO.update(s);
    	assertEquals("temp2", statusDAO.getById(5).getName());
    }

    @Test
    void deleteStatus() {
    	s.setId(5);
    	s.setName("bob");
    	statusDAO.delete(s);
    	assertFalse(statusDAO.getById(5)!= null);
    }
}