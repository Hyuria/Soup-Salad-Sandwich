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
	   
       // Used for resetting the primary key 'id' to either 1 or the next highest number. Used primary in JUNIT tests. 
	   public void resetSequence();

}
