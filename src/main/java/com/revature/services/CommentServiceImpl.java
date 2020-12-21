package com.revature.services;

import com.revature.beans.Like;
import com.revature.data.LikeDAO;
import com.revature.beans.User;
import java.util.Set;
import com.revature.beans.Comment;
import com.revature.data.CommentDAO;
import com.revature.data.CommentHibernate;
import com.revature.data.LikeHibernate;

public class CommentServiceImpl implements CommentService{
   private CommentDAO commentDAO;
   private LikeDAO likeDAO;

   public CommentServiceImpl(){
      commentDAO = new CommentHibernate();
      likeDAO = new LikeHibernate();
   }

   @Override
   public Integer addComment(Comment c){
      return commentDAO.add(c).getId();
   }

   @Override
   public Comment getCommentById(Integer id){
      return commentDAO.getById(id);
   }

   @Override
   public Set<Comment> getAllComments(){
      return commentDAO.getAll();
   }

   @Override
   public void updateComment(Comment c){
      commentDAO.update(c);
   }

   @Override
   public void likeComment(Comment c, User u) {
      Like like = new Like();
      like.setUser(u);
      like.setComment(c);
      likeDAO.add(like);
      c.setLike(c.getLike() + 1);
      updateComment(c);
   }

   @Override
   public void dislikeComment(Comment c, User u) {
      /*
      Like like = new Like();
      like.setUser(u);
      like.setComment(c);
      likeDAO.add(like);
      */
      c.setLike(c.getLike() - 1);
      updateComment(c);
   }

   @Override
   public void deleteComment(Comment c){
      commentDAO.delete(c);
   }
}
