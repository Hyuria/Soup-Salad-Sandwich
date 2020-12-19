package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.*;

import com.revature.beans.Category;
import com.revature.beans.Dish;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DishHibernateTest {
	DishHibernate dishHibernate = new DishHibernate();
	CategoryHibernate categoryHibernate = new CategoryHibernate();
	StatusHibernate statusHibernate = new StatusHibernate();
	Dish dish = new Dish();
	
	@Test
	@Order(1)
	public void add() {
		dish.setStatus(statusHibernate.getById(1));
		dish.setCategory(categoryHibernate.getById(1));
		dish.setName("temp");
		dish.setPhoto_url("temp.temp");
		Dish retDish = dishHibernate.add(dish);
		dish.setId(retDish.getId());
		assertEquals(dish,retDish);
	}
	
	@Test
	@Order(2)
	public void getAll() {
		assertTrue(dishHibernate.getAll().size() > 0);
	}
	
	@Test
	@Order(3)
	public void updateTest() {
		dish.setName("Update");
		dishHibernate.update(dish);
		assertEquals(dish, dishHibernate.getById(dish.getId()));
	}

	@Test
	@Order(5)
	public void deleteTest() {
		dishHibernate.delete(dish);
		assertTrue(dishHibernate.getById(dish.getId()) == null);
	}
}
