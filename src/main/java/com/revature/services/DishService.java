package com.revature.services;

import java.util.Set;

import com.revature.beans.Dish;

public interface DishService {
	 // create
	   public Dish addDish(Dish d);

	   // read
	   public Dish getDishById(Integer id);
	   public Set<Dish> getAll();

	   // update
	   public void updateDish(Dish d);

	   // delete
	   public void deleteDish(Dish d);
}
