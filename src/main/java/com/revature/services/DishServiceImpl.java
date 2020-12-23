package com.revature.services;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Comment;
import com.revature.data.CommentDAO;
import org.springframework.stereotype.*;

import com.revature.beans.Dish;
import com.revature.data.DishDAO;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DishServiceImpl implements DishService {
	private DishDAO dishDAO;
	private CommentDAO commentDAO;
	
	@Autowired
	public DishServiceImpl(DishDAO d, CommentDAO c) {
		dishDAO = d;
		commentDAO = c;
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
		// Get 5 dishes with the most recent activity that are not in recent
		Set<Comment> commentSet = commentDAO.getAll();
		Set<Dish> hotDishes = new HashSet<>();
		Set<Dish> recentlyAddedDishes = getRecentlyAddedDishes();

		while (hotDishes.size() < 5){
			Date lastestTime = null;
			Dish dishToBeAdded = null;
			Comment commentToDelete = null;
			for (Comment c : commentSet){
				// If the comment is recent
				if (lastestTime == null || c.getDate().after(lastestTime)){
					// If the dish associated with the comment is not in recentlyAddedDishes or hotDishes
					if (!hotDishes.contains(c.getDish()) || !recentlyAddedDishes.contains(c.getDish())){
						dishToBeAdded = c.getDish();
						lastestTime = c.getDate();
						commentToDelete = c;
					}
				}
			}
			hotDishes.add(dishToBeAdded);
			commentSet.remove(commentToDelete);
		}
		return null;
	}

	@Override
	public Set<Dish> getRecentlyAddedDishes() {
		Set<Dish> dishSet = dishDAO.getAll();
		Set<Dish> recentDishes = new HashSet<>();
		while(recentDishes.size() < 5){
			Integer highestId = 0;
			Dish dishToBeAdded = null;
			for (Dish d : dishSet){
				if (d.getId() > highestId){
					dishToBeAdded = d;
				}
			}
			recentDishes.add(dishToBeAdded);
			dishSet.remove(dishToBeAdded);
		}
		return recentDishes;
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
