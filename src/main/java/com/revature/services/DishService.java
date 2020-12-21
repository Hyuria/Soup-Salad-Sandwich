package com.revature.services;

import java.util.Set;

import com.revature.beans.Dish;

public interface DishService {
	 // create
	   public Dish addDish(Dish d);

	   // read
	   public Dish getDishById(Integer id);
	   public Set<Dish> getAll();
		public Set<Dish> getDishByCategory(String categoryName);

	   // update
	   public void updateDish(Dish d);

	   // delete
	   public void deleteDish(Dish d);
}
