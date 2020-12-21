package com.revature.controller;

import com.revature.beans.Comment;
import com.revature.beans.Dish;
import com.revature.services.CommentService;
import com.revature.services.CommentServiceImpl;
import com.revature.services.DishService;
import com.revature.services.DishServiceImpl;
import io.javalin.http.Context;

import java.util.Set;

public class DishController {
    private static DishService dishService = new DishServiceImpl();
    private static CommentService commentService = new CommentServiceImpl();


    public static void getAllDish(Context ctx) {
        System.out.println("Getting all Dish");
        Set<Dish> dishes = dishService.getAll();
        if (dishes != null){
            ctx.status(200);
            ctx.json(dishes);
        }else{
            ctx.status(404);
        }
    }

    public static void getDishById(Context ctx) {
        System.out.println("Getting Dish By ID");
        Integer id = Integer.valueOf(ctx.pathParam("id"));
        Dish dish = dishService.getDishById(id);
        if (dish != null){
            ctx.status(200);
        }
    }

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

    public static void deleteDish(Context context) {

    }

    public static void addDish(Context context) {
    }

    public static void getDishByCategory(Context context) {
    }

    public static void getAllComment(Context context) {
    }

    public static void updateComment(Context context) {
    }

    public static void addComment(Context context) {
    }

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

    public static void getCommentByCommentId(Context ctx) {
        Integer dish_id = Integer.valueOf(ctx.pathParam("id"));
        Integer comment_id = Integer.valueOf(ctx.pathParam("comment_id"));
        Set<Comment> commentSet = commentService.getAllComments();
        Comment comment = null;

        for (Comment c : commentSet){
            if (c.getDish().getId() == dish_id && c.getId() == comment_id){
                comment = c;
            }
        }

        if (comment != null){
            ctx.status(200);
            ctx.json(comment);
        }else{
            // Cannot find comment with that id
            ctx.status(404);
        }

    }
}
