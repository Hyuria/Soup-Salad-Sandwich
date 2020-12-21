package com.revature.data;

import java.util.Set;
import com.revature.beans.Dish;

public interface DishDAO extends GenericDAO<Dish> {
    public Dish add(Dish d);
    public Set<Dish> getByCategory(String categoryName);
}
