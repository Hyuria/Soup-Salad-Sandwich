package com.revature.services;

import com.revature.beans.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.beans.Like;
import com.revature.data.LikeDAO;
import com.revature.beans.User;

import java.util.HashSet;
import java.util.Set;
import com.revature.beans.Comment;
import com.revature.data.CommentDAO;

@Service
public class CommentServiceImpl implements CommentService{
   private CommentDAO commentDAO;
   private LikeDAO likeDAO;

   @Autowired
   public CommentServiceImpl(CommentDAO c, LikeDAO l){
      commentDAO = c;
      likeDAO = l;
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
   public Set<Comment> getAllCommentsByDish(Dish d) {
      Set<Comment> commentSet = commentDAO.getAll();
      Set<Comment> filterSet = new HashSet<>();
      for (Comment c : commentSet){
         if (c.getDish().getId() == d.getId()){
            filterSet.add(c);
         }
      }
      return filterSet;
   }

   @Override
   public void updateComment(Comment c){
      commentDAO.update(c);
   }

   @Override
   public void likeComment(Comment c, User u) {
      Set<Like> likeSet = likeDAO.getAll();
      Like existingLike = null;

      for (Like l : likeSet){
         if (l.getUser().getId() == u.getId() && l.getComment().getId() == c.getId()){
            existingLike = l;
         }
      }

      if (existingLike == null){
         // Adding a new Like for User u / Comment c
         Like like = new Like();
         like.setUser(u);
         like.setComment(c);
         like.setLike(1);
         likeDAO.add(like);
         c.setLike(c.getLike() + 1);
         updateComment(c);
      }else{
         if (existingLike.getLike() == 0) {
            // If there exist a dislike, that would be deleted
            likeDAO.delete(existingLike);
            c.setLike(c.getLike() + 1);
            updateComment(c);
         }
         // If there exist a like, do nothing.
      }
   }

   @Override
   public void dislikeComment(Comment c, User u) {
      Set<Like> likeSet = likeDAO.getAll();
      Like existingLike = null;

      for (Like l : likeSet){
         if (l.getUser().getId() == u.getId() && l.getComment().getId() == c.getId()){
            existingLike = l;
         }
      }

      if (existingLike == null){
         // Adding a new dislike for User u / Comment c
         Like like = new Like();
         like.setUser(u);
         like.setComment(c);
         like.setLike(0);
         likeDAO.add(like);
      }else{
         if (existingLike.getLike() == 1) {
            // If there exist a like, that would be deleted
            likeDAO.delete(existingLike);
            c.setLike(c.getLike() - 1);
            updateComment(c);
         }
         // If there exist a dislike, do nothing.
      }
   }

   @Override
   public void deleteComment(Comment c){
      commentDAO.delete(c);
   }
}
