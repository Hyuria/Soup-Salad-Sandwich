package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.*;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LikeHibernateTest {
	private LikeHibernate likeHibernate = new LikeHibernate();
	private UserHibernate userHibernate = new UserHibernate();
	private CommentHibernate commentHibernate = new CommentHibernate();
	private StatusHibernate statusHibernate = new StatusHibernate();
	private CategoryHibernate categoryHibernate = new CategoryHibernate();
	private DishHibernate dishHibernate = new DishHibernate();

	private Like like = new Like();
	private Comment comment = new Comment();
	private Dish dish = new Dish();
	
	 @Test
	 @Order(1)
	 public void addTest() {
	 	dish.setStatus(statusHibernate.getById(1));
	 	dish.setCategory(categoryHibernate.getById(1));
	 	dish.setName("temp");
	 	dish.setPhoto_url("temp.temp");

	 	comment.setUser(userHibernate.getById(1));
	 	comment.setLike(1);
	 	comment.setMessage("TEST");
	 	comment.setDish(dishHibernate.add(dish));


	 	like.setUser(userHibernate.getById(1));
		like.setComment(commentHibernate.add(comment));
		like.setLike(0);
	 	Like retLike = likeHibernate.add(like);
	 	like.setId(retLike.getId());
	 	assertEquals(like, retLike);
	 }

	 @Test
	 @Order(2)
	 void getByIdTest() {
	 	assertEquals(like, likeHibernate.getById(like.getId()));
	 }

	 @Test
	 @Order(3)
	 void getAllTest() {
	 	assertTrue(likeHibernate.getAll().size() > 0);
	 }

	 @Test
	 void updateTest() {
	 	like.setLike(1);
	 	likeHibernate.update(like);
	 	assertEquals(like, likeHibernate.getById(like.getId()));
	 }

	 @Test
	 void deleteTest() {
	 	likeHibernate.delete(like);
	 	commentHibernate.delete(comment);
	 	dishHibernate.delete(dish);
	 	assertTrue(likeHibernate.getById(like.getId()) == null);
	 }
}
