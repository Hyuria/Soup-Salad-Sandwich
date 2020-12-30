package com.revature.services;

import com.revature.beans.Dish;
import com.revature.data.CategoryHibernate;
import com.revature.data.CommentHibernate;
import com.revature.data.DishHibernate;
import com.revature.data.StatusHibernate;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DishServiceImplTest {
    private CategoryHibernate categoryHibernate = new CategoryHibernate();
    private StatusHibernate statusHibernate = new StatusHibernate();

    private DishHibernate dishDao = new DishHibernate();
    private CommentHibernate commentDao = new CommentHibernate();
    private DishServiceImpl dishService = new DishServiceImpl(dishDao, commentDao);

    private Dish dish = new Dish();

    /*
    @Test
    @Order(1)
    void addDish() {
        dish.setCategory(categoryHibernate.getById(4));
        dish.setName("test");
        dish.setStatus(statusHibernate.getById(1));
        dish.setPhoto_url("www.google.com");
        Dish retDish = dishService.addDish(dish);
        dish.setId(retDish.getId());
        assertEquals(dish, retDish);
    }

    @Test
    @Order(2)
    void getDishById() {
        assertEquals(dish, dishService.getDishById(dish.getId()));
    }

    @Test
    @Order(3)
    void getAll() {
        assertTrue(dishService.getAll().size() > 0);
    }

    @Test
    @Order(4)
    void getDishByCategory() {
        assertTrue(dishService.getDishByCategory("undecided").size() > 0);
    }
*/
    @Test
    @Order(5)
    void getHotDishes() {
        assertTrue(dishService.getHotDishes() != null);
    }

//    @Test
//    @Order(6)
//    void getRecentlyAddedDishes() {
//        assertTrue(dishService.getRecentlyAddedDishes().size()>0);
//    }
/*
    @Test
    @Order(7)
    void updateDish() {
        dish.setName("update");
        dishService.updateDish(dish);
        assertEquals(dish, dishService.getDishById(dish.getId()));
    }

    @Test
    @Order(8)
    void deleteDish() {
        dishService.deleteDish(dish);
        assertTrue(dishService.getDishById(dish.getId()) == null);
    }*/
}