package com.revature.controller;

import com.revature.beans.Comment;
import com.revature.beans.Dish;
import com.revature.services.CommentService;
import com.revature.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public DishController(DishService d, CommentService c){
        dishService = d;
        commentService = c;
    }


    @GetMapping
    public static void getAllDish(HttpSession session) {
        System.out.println("Getting all Dish");
        Set<Dish> dishes = dishService.getAll();
        if (dishes != null){
            ctx.status(200);
            ctx.json(dishes);
        }else{
            ctx.status(404);
        }
    }

    @PostMapping
    public static void addDish(Context ctx) {
        Dish dish = ctx.bodyAsClass(Dish.class);
        dishService.addDish(dish);
        ctx.status(201);
    }


    // PATH = /id
    @GetMapping(path = "/{id}")
    public static void getDishById(Context ctx) {
        System.out.println("Getting Dish By ID");
        Integer id = Integer.valueOf(ctx.pathParam("id"));
        Dish dish = dishService.getDishById(id);
        if (dish != null){
            ctx.status(200);
        }
    }

    @PutMapping(path = "/{id}")
    public static void updateDish(Context ctx) {
        System.out.println("Updating Dish");
        Integer id = Integer.valueOf(ctx.pathParam("id"));
        Dish dish = ctx.bodyAsClass(Dish.class);
        if (dish != null){
            ctx.status(200);
            dishService.updateDish(dish);
        }else{
            ctx.status(404);
        }
    }

    @DeleteMapping(path = "/{id}")
    public static void deleteDish(Context ctx) {
      Integer id = Integer.valueOf(ctx.pathParam("id"));
      Dish dish = dishService.getDishById(id);
      if(dish != null){
         dishService.deleteDish(dish);
          ctx.status(204);
      }
      else{
          ctx.status(204);
      }
    }

    @GetMapping(path = "/{category}")
   public static void getDishByCategory(Context ctx) {
      String categoryName = ctx.pathParam("category");
      System.out.println("Getting " + categoryName + " dishes...");
      Set<Dish> dishSet = dishService.getDishByCategory(categoryName);
      if(dishSet != null){
          ctx.status(200);
          ctx.json(dishSet);
      }
      else{
          ctx.status(404);
      }
   }

   @GetMapping(path = "/{id}/comment")
    public ResponseEntity<Set<Comment>> getAllComment(HttpSession session) {
        System.out.println("Retrieving comments");
        Set<Comment> comments = commentService.getAllComments();
        if (comments != null){
            return ResponseEntity.ok(comments);      
        }else{
            return ResponseEntity.notFound().build();
        }
    }
  
    @GetMapping(path = "/{id}/comment/{comment_id}")
    public ResponseEntity<Comment> getCommentByCommentId(HttpSession session) {
    	Integer dish_id = (Integer) session.getAttribute("id");
    	Integer comment_id = (Integer) session.getAttribute("comment_id");
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
    public static ResponseEntity<Comment> updateComment(HttpSession session, @RequestBody Comment c) {
    	System.out.println("Updating comment");
        Integer id = (Integer) session.getAttribute("id");
        Comment comment = commentService.getCommentById(id);
        if (comment != null){	
            commentService.updateComment(comment);
            return ResponseEntity.ok(comment);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/{id}/comment/")
    public static void addComment(Context ctx) {
    	System.out.println("Inserting a new Comment");
    	Comment comment = ctx.bodyAsClass(Comment.class);
        if (comment != null){
            ctx.status(200);
            commentService.addComment(comment);;
        }else{
            ctx.status(404);
        }
    }

    @DeleteMapping(path = "/{id}/comment/{comment_id}")
    public static void deleteComment(Context ctx) {
        Integer comment_id = Integer.valueOf(ctx.pathParam("comment_id"));
        Comment comment = commentService.getCommentById(comment_id);

        if (comment != null){
            commentService.deleteComment(comment);
            ctx.status(200);
        }else{
            ctx.status(404);
        }
    }



    public static void getRecentlyAddedDishes(Context context) {

    }

    public static void getHotDishes(Context context) {

    }
}
