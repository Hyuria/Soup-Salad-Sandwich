package com.revature.data;

import java.util.Set;
import com.revature.beans.Dish;
import com.revature.exception.NonUniqueDishException;

public interface DishDAO extends GenericDAO<Dish> {
    public Dish add(Dish d);
    public Set<Dish> getByCategory(String categoryName);
}
