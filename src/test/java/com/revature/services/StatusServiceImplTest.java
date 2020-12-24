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
    void getStatusById() {
    	s = statusDAO.getById(1);
    	assertEquals("admin pending",s.getName());
    }

    @Test
    void getAll() {
    	statusSet = statusDAO.getAll();
    	assertTrue(statusSet.size()>0);
    }

}