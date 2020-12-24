package com.revature.services;


import com.revature.beans.User;
import com.revature.beans.Like;
import com.revature.data.CategoryHibernate;
import com.revature.data.CategoryDAO;
import com.revature.data.UserHibernate;
import com.revature.data.UserDAO;
import com.revature.data.DishHibernate;
import com.revature.data.DishDAO;
import com.revature.data.StatusHibernate;
import com.revature.data.StatusDAO;
import org.junit.jupiter.api.*;
import com.revature.beans.Dish;
import com.revature.beans.Comment;
import com.revature.data.LikeHibernate;
import com.revature.data.CommentHibernate;
import com.revature.data.LikeDAO;
import com.revature.data.CommentDAO;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommentServiceImplTest {
   private CommentDAO commentDAO = new CommentHibernate();
   private LikeDAO likeDAO = new LikeHibernate();
   private StatusDAO statusDAO = new StatusHibernate();
   private DishDAO dishDAO = new DishHibernate();
   private UserDAO userDAO = new UserHibernate();
   private CategoryDAO categoryDAO = new CategoryHibernate();

   private CommentService commentService = new CommentServiceImpl(commentDAO, likeDAO);

   private Comment comment = new Comment();
   private Dish dish = new Dish();
   private Like like = new Like();
   private User user = new User();

   @Test
   @Order(1)
   void addComment() {
      dish.setStatus(statusDAO.getById(1));
      dish.setCategory(categoryDAO.getById(1));
      dish.setName("temp");
      dish.setPhoto_url("temp.temp");

      comment.setUser(userDAO.getById(1));
      comment.setLike(1);
      comment.setMessage("TEST");
      comment.setDish(dishDAO.add(dish));

      Comment retComment = commentService.getCommentById(commentService.addComment(comment));
      comment.setId(retComment.getId());
      assertEquals(comment, retComment);
   }

   @Test
   @Order(2)
   void getCommentById() {
      assertEquals(comment, commentService.getCommentById(comment.getId()));
   }

   @Test
   @Order(3)
   void getAllComments() {
      assertTrue(commentService.getAllComments().size() > 0);
   }

   @Test
   @Order(4)
   void getAllCommentsByDish() {
      assertTrue(commentService.getAllCommentsByDish(dish).size() > 0);
   }

   @Test
   @Order(5)
   void updateComment() {
      comment.setMessage("TEST UPDATE");
      commentService.updateComment(comment);
      assertEquals(comment, commentService.getCommentById(comment.getId()));
   }

   @Test
   @Order(6)
   void likeComment() {
      like.setComment(comment);
      like.setLike(1);
      like.setUser(user);
      likeDAO.add(like);

      comment.setLike(comment.getLike() + 1);
      commentService.updateComment(comment);
      assertEquals(comment, commentService.getCommentById(comment.getId()));
   }

   @Test
   @Order(7)
   void dislikeComment() {
      like.setComment(comment);
      like.setLike(0);
      like.setUser(user);
      likeDAO.add(like);

      comment.setLike(comment.getLike() - 1);
      commentService.updateComment(comment);
      assertEquals(comment, commentService.getCommentById(comment.getId()));
   }

   @Test
   @Order(8)
   void deleteComment() {
      commentService.deleteComment(comment);
      dishDAO.delete(dish);
      likeDAO.delete(like);
      userDAO.delete(user);
   }
}
