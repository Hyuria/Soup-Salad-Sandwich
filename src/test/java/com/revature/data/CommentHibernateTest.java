package com.revature.data;

import com.revature.beans.Comment;
import com.revature.beans.Dish;
import com.revature.beans.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.*;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentHibernateTest {
    private CommentHibernate commentHibernate = new CommentHibernate();
    private DishHibernate dishHibernate = new DishHibernate();
    private UserHibernate userHibernate = new UserHibernate();
    private StatusHibernate statusHibernate = new StatusHibernate();
    private CategoryHibernate categoryHibernate = new CategoryHibernate();

    private Comment comment = new Comment();
    private Dish dish = new Dish();

    @Test
    @Order(1)
    void add(){
        dish.setStatus(statusHibernate.getById(1));
        dish.setCategory(categoryHibernate.getById(1));
        dish.setName("temp");
        dish.setPhoto_url("temp.temp");

        comment.setUser(userHibernate.getById(1));
        comment.setLike(1);
        comment.setMessage("TEST");
        comment.setDish(dishHibernate.add(dish));
        Comment retComment = commentHibernate.add(comment);
        comment.setId(retComment.getId());
        assertEquals(comment, retComment);
    }

    @Test
    @Order(2)
    void getById() {
        assertEquals(comment, commentHibernate.getById(comment.getId()));
    }

    @Test
    @Order(3)
    void getAll() {
        assertTrue(commentHibernate.getAll().size() > 0);
    }

    @Test
    @Order(4)
    void update() {
        comment.setMessage("TEST UPDATE");
        commentHibernate.update(comment);
        assertEquals(comment, commentHibernate.getById(comment.getId()));
    }

    @Test
    @Order(5)
    void delete() {
        commentHibernate.delete(comment);
        dishHibernate.delete(dish);
        assertTrue(commentHibernate.getById(comment.getId()) == null);
    }
}
