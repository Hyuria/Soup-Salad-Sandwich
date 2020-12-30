package com.revature.controller;

import com.revature.beans.*;
import com.revature.exception.NonUniqueDishException;
import com.revature.services.CommentService;
import com.revature.services.DishService;
import com.revature.services.LikeService;
import com.revature.services.VoteService;
import com.revature.exception.AlreadyVotedException;
import com.revature.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Set;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
@RequestMapping(path="/dish")
public class DishController {
    private static DishService dishService;
    private static CommentService commentService;
    private static VoteService voteService;
    private static LikeService likeService;
    private static UserService userService;
    private static CategoryService categoryService;


    @Autowired
    public DishController(DishService d, CommentService c, VoteService v, LikeService l, UserService u, CategoryService cat){
        dishService = d;
        commentService = c;
        voteService = v;
        likeService = l;
        userService = u;
        categoryService = cat;
    }


    @GetMapping
    public ResponseEntity<Set<Dish>> getAllDish(HttpSession session) {
        System.out.println("Getting all Dish");
		Set<Dish> dishSet = dishService.getAll();
        if (dishSet.size() == 0)
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(dishSet);
    }

    @PostMapping
    public ResponseEntity<Void> addDish(HttpSession session, @RequestBody Dish d) {
        System.out.println("Adding new dish");
    	
			Dish dish= dishService.addDish(d);
    	 
        return ResponseEntity.ok().build();
    }


    // PATH = /id
    @GetMapping(path = "/{id}")
    public ResponseEntity<Dish> getDishById(HttpSession session, @PathVariable("id") Integer id) {
        System.out.println("Getting Dish By ID");
        Dish dish = dishService.getDishById(id);
		if (dish == null)
			return ResponseEntity.badRequest().build();
        System.out.println(dish);
		return ResponseEntity.ok(dish);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> updateDish(HttpSession session, @PathVariable("id") Integer id, @RequestBody Dish newDish) {
    	System.out.println("[Dish Controller] Updating Dish: " + newDish.getName());
    	Dish oldDish = dishService.getDishById(id);
    	if (oldDish != null) {
    		dishService.updateDish(newDish);
    		return ResponseEntity.ok().build();
    	}
    	else{
			return ResponseEntity.notFound().build();
    	}
	}

   @DeleteMapping(path = "/{id}")
   public ResponseEntity<Void> deleteDish(@PathVariable("id") Integer id) {
      Dish dish = dishService.getDishById(id);
      if(dish != null){
         dishService.deleteDish(dish);
         return ResponseEntity.ok().build();
      }
      else{
         return ResponseEntity.notFound().build();
      }
   }

   @GetMapping(path = "/category/{category}")
   public ResponseEntity<Set<Dish>> getDishByCategory(@PathVariable("category") String categoryName) {
      System.out.println("Getting " + categoryName + " dishes...");
      Set<Dish> dishSet = dishService.getDishByCategory(categoryName);
      if(dishSet != null){
         return ResponseEntity.ok(dishSet);
      }
      else{
         return ResponseEntity.notFound().build();
      }
   }

    @GetMapping(path = "/{id}/like")
    public ResponseEntity<Set<Like>> getLikeByDish(@PathVariable("id") Integer id) {
        System.out.println("Retrieving Likes for Dish#: " + id);
        Set<Like> likeSet = likeService.getLikeByDishId(id);
        System.out.println("Retrieved Likes: " + likeSet);
        if (likeSet != null){
            return ResponseEntity.ok(likeSet);
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping(path = "/{id}/vote")
    public ResponseEntity<Set<Vote>> getAllVote(@PathVariable("id") Integer id) {
        System.out.println("Retrieving Vote for Dish#:" + id);
        Set<Vote> voteSet = voteService.getVoteByDishId(id);
        System.out.println("Retrieved Votes: " + voteSet);
        if (voteSet != null){
            return ResponseEntity.ok(voteSet);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/vote/{category_id}/user/{user_id}")
    public ResponseEntity<Status> addVote(@PathVariable("id") Integer dish_id, @PathVariable("category_id") Integer category_id, @PathVariable("user_id") Integer user_id){
        System.out.println("User :" + user_id + " voted category: " + category_id + " for dish: " + dish_id);
        try{
            voteService.categoryVote(userService.getUserById(user_id), dishService.getDishById(dish_id), categoryService.getCategoryById(category_id));
        }catch (AlreadyVotedException e){
            System.out.println("User already voted");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }



   @GetMapping(path = "/{id}/comment")
   public ResponseEntity<Set<Comment>> getAllComment(@PathVariable("id") Integer id) {
      System.out.println("Retrieving comments By Dish#: " + id);
      Set<Comment> comments = commentService.getAllCommentsByDish(dishService.getDishById(id));
      System.out.println("Retrieved Comments: " + comments);
      if (comments != null){
         return ResponseEntity.ok(comments);
      }else{
         return ResponseEntity.notFound().build();
      }
   }

    @GetMapping(path = "/{id}/comment/{comment_id}")
    public ResponseEntity<Comment> getCommentByCommentId(HttpSession session, @PathVariable("id") Integer dish_id, @PathVariable("comment_id") Integer comment_id) {
        Set<Comment> commentSet = commentService.getAllComments();
        Comment comment = null;
        for (Comment c : commentSet){
            if (c.getDish().getId() == dish_id && c.getId() == comment_id){
                comment = c;
            }
        }
        if (comment != null){
            return ResponseEntity.ok(comment);
        }else{
            // Cannot find comment with that id
            return ResponseEntity.notFound().build(); 
        }
    }

    @PutMapping(path = "/{id}/comment/{comment_id}")
    public ResponseEntity<Comment> updateComment(HttpSession session, @RequestBody Comment c, @PathVariable("comment_id") Integer comment_id) {
    	System.out.println("Updating comment");
        if (c != null && c.getId() == comment_id){
            commentService.updateComment(c);
            return ResponseEntity.ok(c);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/{id}/comment/{user_id}")
    public ResponseEntity<Status> addComment(HttpSession session, @RequestBody String message, @PathVariable("id") Integer dish_id, @PathVariable("user_id") Integer user_id) {
    	System.out.println("Inserting a new Comment for Dish: " + dish_id + " by User: " + user_id + " with content: " + message);
    	Comment c = new Comment();
    	c.setDish(dishService.getDishById(dish_id));
    	c.setUser(userService.getUserById(user_id));
    	c.setMessage(message);
    	Integer retValue = commentService.addComment(c);
        if (retValue != null){
            return ResponseEntity.ok().build();
        }else{
        	return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(path = "/{id}/comment/{comment_id}")
    public ResponseEntity<Status> deleteComment(HttpSession session, @PathVariable("comment_id") Integer comment_id) {
        Comment comment = commentService.getCommentById(comment_id);
        if (comment != null){
            commentService.deleteComment(comment);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/comment/{comment_id}/like/{user_id}")
    public ResponseEntity<Status> likeComment(HttpSession session, @PathVariable("comment_id") Integer comment_id, @PathVariable("user_id") Integer user_id) {
        System.out.println("User : " + user_id + " liking comment: " + comment_id);
        if (comment_id != null && user_id != null){
            commentService.likeComment(commentService.getCommentById(comment_id), userService.getUserById(user_id));
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping (path = "/comment/{comment_id}/like/{user_id}")
    public ResponseEntity<Status> dislikeComment(HttpSession session, @PathVariable("comment_id") Integer comment_id, @PathVariable("user_id") Integer user_id) {
        System.out.println("Disliking a Comment");
        if (comment_id != null && user_id != null){
            commentService.dislikeComment(commentService.getCommentById(comment_id), userService.getUserById(user_id));
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }



    @GetMapping(path = "/recent")
    public ResponseEntity<Set<Dish>> getRecentlyAddedDishes(HttpSession session) {
        Set<Dish> recentDishes = dishService.getRecentlyAddedDishes();
        if (recentDishes != null){
            return ResponseEntity.ok(recentDishes);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/hot")
    public ResponseEntity<Set<Dish>> getHotDishes(HttpSession session) {
        Set<Dish> hotDishes = dishService.getHotDishes();
        if (hotDishes != null){
            return ResponseEntity.ok(hotDishes);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/pending")
    public ResponseEntity<Set<Dish>> getPendingDishes(HttpSession session) {
        Set<Dish> pendingDishes = dishService.getPendingDishes();
        if (pendingDishes != null){
            return ResponseEntity.ok(pendingDishes);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
