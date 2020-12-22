package com.revature.services;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.*;

import com.revature.beans.Dish;
import com.revature.data.DishDAO;
import com.revature.data.DishHibernate;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DishServiceImpl implements DishService {
	private DishDAO dishDAO;
	
	@Autowired
	public DishServiceImpl(DishDAO d) {
		dishDAO = d;
	}

	@Override
	public Dish addDish(Dish d) {
		return dishDAO.add(d);
	}

	@Override
	public Dish getDishById(Integer id) {
		return dishDAO.getById(id);
	}

	@Override
	public Set<Dish> getAll() {
		return dishDAO.getAll();
	}

	@Override
	public Set<Dish> getDishByCategory(String categoryName){
		return dishDAO.getByCategory(categoryName);
	}

	@Override
	public Set<Dish> getHotDishes() {
		// Get 5 dishes with the most recent activity that are not in recents
		Set<Dish> dishSet = dishDAO.getAll();
		Set<Dish> filterSet = new HashSet<>();



		return null;
	}

	@Override
	public Set<Dish> getRecentlyAddedDishes() {
		Set<Dish> dishSet = dishDAO.getAll();
		Set<Dish> recentDish = new HashSet<>();
		while(recentDish.size() <= 5){
			Integer highestId = 0;
			Dish dishToBeAdded = null;
			for (Dish d : dishSet){
				if (d.getId() > highestId){
					dishToBeAdded = d;
				}
			}
			recentDish.add(dishToBeAdded);
			dishSet.remove(dishToBeAdded);
		}
		return recentDish;
	}


	@Override
	public void updateDish(Dish d) {
		dishDAO.update(d);
	}

	@Override
	public void deleteDish(Dish d) {
		dishDAO.delete(d);
	}
}
