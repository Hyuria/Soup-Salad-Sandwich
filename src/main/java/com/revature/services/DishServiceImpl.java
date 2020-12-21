package com.revature.services;

import java.util.Set;

import com.revature.beans.Dish;
import com.revature.data.DishDAO;
import com.revature.data.DishHibernate;

public class DishServiceImpl implements DishService {
	private DishDAO dishDAO;
	
	public DishServiceImpl() {
		dishDAO = new DishHibernate();
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
	public void updateDish(Dish d) {
		dishDAO.update(d);
	}

	@Override
	public void deleteDish(Dish d) {
		dishDAO.delete(d);
	}

	@Override
	public void resetSequence() {
		DishHibernate dh = new DishHibernate();
		dh.resetSequence();
	}

}
