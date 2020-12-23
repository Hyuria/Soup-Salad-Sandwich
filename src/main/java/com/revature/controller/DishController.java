package com.revature.controller;

import com.revature.beans.Comment;
import com.revature.beans.Dish;
import com.revature.beans.Status;
import com.revature.services.CommentService;
import com.revature.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Set;
import java.util.HashSet;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
@RequestMapping(path="/dish")
public class DishController {
    private static DishService dishService;
    private static CommentService commentService;

    @Autowired
    public DishController(DishService d, CommentService c){
        dishService = d;
        commentService = c;
    }


    @GetMapping
    public static ResponseEntity<Set<Dish>> getAllDish(HttpSession session) {
        System.out.println("Getting all Dish");
		Set<Dish> dishSet = dishService.getAll();
        if (dishSet.size() == 0)
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(dishSet);
    }

    @PostMapping
    public static ResponseEntity<Void> addDish(HttpSession session, @RequestBody Dish d) {
        System.out.println("Adding new dish");
    	dishService.addDish(d);
        return ResponseEntity.ok().build();
    }


    // PATH = /id
    @GetMapping(path = "/{id}")
    public static ResponseEntity<Dish> getDishById(HttpSession session, @PathVariable("id") Integer id) {
        System.out.println("Getting Dish By ID");
        Dish dish = dishService.getDishById(id);
		if (dish == null)
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(dish);

    }

    @PutMapping(path = "/{id}")
    public static ResponseEntity<Void> updateDish(HttpSession session, @PathVariable("id") Integer id, @RequestBody Dish dish) {
    		System.out.println("Updating Dish");
    		Dish d = dishService.getDishById(dish.getId());
    		if (d != null && d.getId().equals(id)) {
    			dishService.updateDish(dish);
    			return ResponseEntity.ok().build();
    		}
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    	}

   @DeleteMapping(path = "/{id}")
   public static ResponseEntity<Void> deleteDish(@PathVariable("id") Integer id) {
      Dish dish = dishService.getDishById(id);
      if(dish != null){
         dishService.deleteDish(dish);
         return ResponseEntity.ok().build();
      }
      else{
         return ResponseEntity.notFound().build();
      }
   }

   @GetMapping(path = "/{category}")
   public static ResponseEntity<Set<Dish>> getDishByCategory(@PathVariable("category") String categoryName) {
      System.out.println("Getting " + categoryName + " dishes...");
      Set<Dish> dishSet = dishService.getDishByCategory(categoryName);
      if(dishSet != null){
         return ResponseEntity.ok(dishSet);
      }
      else{
         return ResponseEntity.notFound().build();
      }
   }

   @GetMapping(path = "/{id}/comment")
   public static ResponseEntity<Set<Comment>> getAllComment(@PathVariable("id") Integer id) {
      System.out.println("Retrieving comments");
      Set<Comment> comments = commentService.getAllCommentsByDish(dishService.getDishById(id));
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
    public static ResponseEntity<Comment> updateComment(HttpSession session, @RequestBody Comment c, @PathVariable("comment_id") Integer comment_id) {
    	System.out.println("Updating comment");
        if (c != null && c.getId() == comment_id){
            commentService.updateComment(c);
            return ResponseEntity.ok(c);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/{id}/comment/")
    public static ResponseEntity<Status> addComment(HttpSession session, @RequestBody Comment c, @PathVariable("id") Integer dish_id) {
    	System.out.println("Inserting a new Comment");
        if (c != null){
            c.setDish(dishService.getDishById(dish_id));
            commentService.addComment(c);
            return ResponseEntity.ok().build();
        }else{
        	return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}/comment/{comment_id}")
    public static ResponseEntity<Status> deleteComment(HttpSession session, @PathVariable("comment_id") Integer comment_id) {
        Comment comment = commentService.getCommentById(comment_id);
        if (comment != null){
            commentService.deleteComment(comment);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    // need to figure out path
    @GetMapping(path = "/comment/{comment_id")
    public static void getRecentlyAddedDishes(HttpSession session) {

    }

    @GetMapping(path = "{id}/hotdish")
    public static void getHotDishes(HttpSession session) {

    }
}
