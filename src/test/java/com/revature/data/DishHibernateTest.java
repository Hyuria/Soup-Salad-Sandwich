package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.Category;
import com.revature.beans.Dish;

public class DishHibernateTest {
	Dish d = new Dish();
	Category c = new Category();
	DishHibernate dh = new DishHibernate();
	Set<Dish> dishSet = new HashSet<>();
	
	@Test
	public void add() {
		Dish dish = new Dish();
		d.setName("temp");
		d.setPhoto_url("temp.temp");
		dish = dh.add(d);
		
		assertEquals(4,dish.getCategory().getId());
	}
	
	@Test
	public void getAll() {
		dishSet = dh.getAll();
		
		assertTrue(dishSet.size()>0);
	}
	
	@Test
	public void updateTest() {
		d.setName("Update");
		dh.update(d);
		
		assertEquals(d, dh.getById(d.getId()));	
	}

	@Test
	public void deleteTest() {
		 dh.delete(d);
	        assertTrue(dh.getById(d.getId()) == null);
	}
}
