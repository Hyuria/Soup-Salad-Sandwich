package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.revature.beans.Comment;
import com.revature.beans.Like;
import com.revature.beans.Role;
import com.revature.beans.User;

public class LikeHibernateTest {
	Like l = new Like();
	LikeHibernate lh = new LikeHibernate();
	Set<Like> likeSet = new HashSet<>();
	User u = new User();
	Comment c = new Comment();
	
	 @Test
	 public void addTest() {
		 u.setId(2);
		 c.setId(2);
	     l.setUser(u);
	     l.setComment(c);
	     
	     assertEquals(l, lh.add(l));
	    }

	    @Test
	    void getByIdTest() {
	        assertEquals(l, lh.getById(l.getId()));
	    }

	    @Test
	    void getAllTest() {
	        assertTrue(lh.getAll().size() > 0);
	    }

	    @Test
	    void updateTest() {
	        l.setLike(1);
	        lh.update(l);
	        assertEquals(l, lh.getById(l.getId()));
	    }

	    @Test
	    void deleteTest() {
	        lh.delete(l);
	        assertTrue(lh.getById(l.getId()) == null);
	    }

}
