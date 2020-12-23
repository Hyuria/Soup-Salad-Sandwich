package com.revature.services;

import com.revature.beans.Dish;
import com.revature.beans.User;
import java.util.Set;
import com.revature.beans.Comment;

public interface CommentService{
   // create
   public Integer addComment(Comment c);

   // read
   public Comment getCommentById(Integer id);
   public Set<Comment> getAllComments();
   public Set<Comment> getAllCommentsByDish(Dish dish);

   // update
   public void updateComment(Comment c);
   public void likeComment(Comment c, User u);
   public void dislikeComment(Comment c, User u);

   // delete
   public void deleteComment(Comment c);
}
