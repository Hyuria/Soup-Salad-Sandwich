package com.revature.services;

import org.junit.jupiter.api.Test;

import com.revature.beans.Vote;
import com.revature.data.CategoryDAO;
import com.revature.data.CategoryHibernate;
import com.revature.data.DishDAO;
import com.revature.data.DishHibernate;
import com.revature.data.UserDAO;
import com.revature.data.UserHibernate;
import com.revature.data.VoteDAO;
import com.revature.data.VoteHibernate;
import com.revature.exception.AlreadyVotedException;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

class VoteServiceImplTest {
	Vote v = new Vote();
	VoteDAO voteDAO = new VoteHibernate();
	DishDAO dishDAO = new DishHibernate();
	UserDAO userDAO = new UserHibernate();
	CategoryDAO categoryDAO = new CategoryHibernate();
	
	Set<Vote> voteSet = new HashSet<>();

    @Test
    void addVote() throws AlreadyVotedException {
    	v.setDish(dishDAO.getById(1));
    	v.setUser(userDAO.getById(2));
    	v.setCategory(categoryDAO.getById(1));
    	v = voteDAO.add(v);
    	assertEquals(1, v.getDish().getId()); 
        assertEquals(2, v.getUser().getId());
        assertEquals(1, v.getCategory().getId());  	
    }

    @Test
    void getVoteById() {
        v = voteDAO.getById(1);
        assertEquals(1, v.getId());
        assertEquals(1, v.getDish().getId());
        assertEquals(1, v.getUser().getId());
        assertEquals(1, v.getCategory().getId());    
    }

    @Test
    void getAll() {
    	voteSet = voteDAO.getAll();
    	assertTrue(voteSet.size() > 0);
    }

    @Test
    void updateVote() {
    	v.setId(2);
      	v.setDish(dishDAO.getById(1));
    	v.setUser(userDAO.getById(3));
    	v.setCategory(categoryDAO.getById(4));
    	voteDAO.update(v);
    	assertEquals(1, v.getDish().getId()); 
    	assertEquals(3, v.getUser().getId());
    	assertEquals(4, v.getCategory().getId());  
    }

    @Test
    void categoryVote() {
    	// what's this?
    }

    @Test
    void deleteVote() {
    	v.setId(2);
    	voteDAO.delete(v);
    	assertFalse(voteDAO.getById(2) !=null);  	
    }
}