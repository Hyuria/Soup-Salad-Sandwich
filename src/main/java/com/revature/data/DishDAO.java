package com.revature.data;

import com.revature.beans.Dish;

public interface DishDAO extends GenericDAO<Dish> {
    public Dish add(Dish d);
}
